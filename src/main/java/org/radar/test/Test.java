package org.radar.test;

import org.radar.analysis.annotations.Rationale;

@Rationale(
		id = "1.0",
		context = {"API REST de facturaci�n, Conexi�n a proyecto con BD firebird"},
		justification = {"Spring boot brinda caracter�sticas como: balanceo de carga,"
				+ " servidor de descubrimiento Eureka Server, GateWay Zuul",
				"Spring Security provee autenticaci�n basada en tokens"},
		consequence = {"EJBs y NodeJs no brindan herramientas para balanceo de carga y "
				+ "trazabilidad distribuida se deben implementar"},
		alternative = {"Spring Boot", "EJBs", "NodeJs", 
				"Para el broker de mensajeria tambien se puede usar rabbitmq, sin embargo, "
				+ "repite los mensajes revisar"},
		decision = {"Implementar Microservicio en spring boot, rendimiento, seguridad, interoperabilidad",
				   "Esta desplegado en un contenedor docker port:80"},
		pattern = {"Patr�n Cliente Servidor,", "N-Tiers", "Patr�n publicador subscriptor"},
		tactic = {"Detecci�n, Heart Beat", "Incluir procesamiento concurrente mediante hilos",
				 "Almacenamiento en cache", "M�todos asincr�nicos", 
				 "Implementar el broker de mensajer�a Apache Kafka"},
		strategy = {""}
)
public class Test {

}
