# Retrospectiva del Sprint 2

## Base de la retrospectiva

Este registro se elabora el 29 de octubre de 2026 a partir del código, las pruebas y la ejecución local de `mvn clean verify` en `feature/HU-02`. El repositorio no incluye acta de conversación del equipo, participantes ni acuerdos previos; por eso, los puntos siguientes son observaciones técnicas y acciones propuestas, no opiniones atribuidas al equipo.

## Qué funcionó

- Los servicios tienen contratos por interfaz y sus dependencias se reciben por constructor.
- El cálculo de flete está aislado detrás de `EstrategiaFlete`, lo que permite añadir una estrategia sin editar el selector.
- Las pruebas ejercitan errores de cédula y correo duplicados, productor inexistente, validaciones de entrada, municipio sin tarifa y filtros vacíos.
- La última verificación local terminó con 27 pruebas sin fallos, cero violaciones de Checkstyle, 95,93% de cobertura de instrucciones y 100% de ramas.
- Los diagramas y los mockups se generaron como archivos versionables, con fuente SVG/HTML para facilitar futuras modificaciones.

## Qué requiere atención

- Las tarifas municipales de flete aún son valores de ejemplo y necesitan confirmación del Product Owner.
- La verificación verde es local; no hay una ejecución de Actions asociada a estos cambios porque no se han enviado al remoto.
- Los documentos de Review y Retrospective no estaban presentes en el repositorio; este registro se basa solo en evidencia técnica disponible.
- La validación de los mockups y la evidencia del tablero no están registradas.

## Acciones propuestas

| Acción | Criterio de cierre |
|---|---|
| Confirmar tarifas con el Product Owner | Valores aprobados y reflejados en la configuración y sus pruebas. |
| Integrar por pull request hacia `develop` | Pull request revisado y check `build` verde en Actions. |
| Revisar los mockups con el Product Owner | Aprobación o cambios solicitados documentados. |
| Completar tablero y evidencia visual | Tablero público accesible, columnas y límites WIP configurados y tarjetas en el estado acordado. |
| Realizar la conversación de retrospectiva con el equipo | Participantes y acuerdos reales registrados en una actualización posterior de este documento. |

## Cierre

La calidad técnica local satisface los umbrales configurados en Maven. La aceptación funcional, la integración remota y los acuerdos del equipo quedan pendientes de evidencia; no se consideran completados por esta retrospectiva técnica.
