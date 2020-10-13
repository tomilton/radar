package org.radar.analysis.implementations;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;
import org.radar.analysis.interfaces.Reflection;
import org.radar.analysis.annotations.Rationale;
import org.radar.analysis.models.Annotation;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

public class ReflectionImplJar implements Reflection {

    private Reflections reflections;

    @Override
    public Set<Method> getMethodsAnnotatedWithRationale() {
        return reflections.getMethodsAnnotatedWith(Rationale.class);
    }

    @Override
    public Set<Class<?>> getClasesAnnotatedWhitRationale() {
        return reflections.getTypesAnnotatedWith(Rationale.class);
    }

    @Override
    public HashMap<Annotation, Rationale> getRationaleAnnotation() {
        Set<Class<?>> annotatedClasses = getClasesAnnotatedWhitRationale();
        Set<Method> annotatedMethods = getMethodsAnnotatedWithRationale();
        HashMap<Annotation, Rationale> data = new HashMap<>();

        annotatedMethods.forEach((annotatedMethod) -> {
            String aux[] = annotatedMethod.toString().split(" ");
            String path = aux[aux.length - 1];
            String name = annotatedMethod.getName();
            String type = annotatedMethod.getClass().getSimpleName();
            Annotation info = new Annotation(path, name, type);
            for (Rationale rationale : annotatedMethod.getAnnotationsByType(Rationale.class)) {
                data.put(info, rationale);
            }
        });

        annotatedClasses.forEach((annotatedClass) -> {
            String path = annotatedClass.getCanonicalName();
            String name = annotatedClass.getSimpleName();
            String type = annotatedClass.getClass().getSimpleName();
            Annotation info;
            if (name.equals("package-info")) {
                info = new Annotation(path, name, "Package");
            } else {
                info = new Annotation(path, name, type);
            }
            for (Rationale rationale : annotatedClass.getAnnotationsByType(Rationale.class)) {
                data.put(info, rationale);
            }
        });

        return data;
    }

    @Override
    public void configureReflection(String modelPackage) {
        // Se debe especificar el tipo de scanners que se quieren ejecutar (Type y
        // Method)
        // Se debe especificar la url del paquete que se quiere inspeccionar
        // se debe agregar un filtro para realizar las busquedas en el paquete
        reflections = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage(modelPackage))
                .setScanners(new TypeAnnotationsScanner(), new MethodAnnotationsScanner())
                .filterInputsBy(new FilterBuilder().includePackage(modelPackage)));

    }

}
