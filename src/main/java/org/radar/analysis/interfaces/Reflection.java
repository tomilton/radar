package org.radar.analysis.interfaces;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;
import org.radar.analysis.annotations.Rationale;
import org.radar.analysis.models.Annotation;

public interface Reflection {

    public abstract Set<Method> getMethodsAnnotatedWithRationale();

    public abstract Set<Class<?>> getClasesAnnotatedWhitRationale();

    public abstract HashMap<Annotation, Rationale> getRationaleAnnotation();

    public abstract void configureReflection(String modelPackage);

}
