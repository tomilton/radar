package org.radar.test;

import org.radar.analysis.annotations.Rationale;

@Rationale(
		id = "1.0",
		context = {"API REST de facturación, Conexión a proyecto con BD firebird"},
		justification = {"Spring boot brinda características como: balanceo de carga,"
				+ " servidor de descubrimiento Eureka Server, GateWay Zuul",
				"Spring Security provee autenticación basada en tokens"},
		consequence = {"EJBs y NodeJs no brindan herramientas para balanceo de carga y "
				+ "trazabilidad distribuida se deben implementar"},
		alternative = {"Spring Boot", "EJBs", "NodeJs", 
				"Para el broker de mensajeria tambien se puede usar rabbitmq, sin embargo, "
				+ "repite los mensajes revisar"},
		decision = {"Implementar Microservicio en spring boot, rendimiento, seguridad, interoperabilidad",
				   "Esta desplegado en un contenedor docker port:80"},
		pattern = {"Patrón Cliente Servidor,", "N-Tiers", "Patrón publicador subscriptor"},
		tactic = {"Detección, Heart Beat", "Incluir procesamiento concurrente mediante hilos",
				 "Almacenamiento en cache", "Métodos asincrónicos", 
				 "Implementar el broker de mensajería Apache Kafka"},
		strategy = {""}
)
public class Test {

}
