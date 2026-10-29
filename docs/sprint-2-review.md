# Sprint 2 Review

## Alcance presentado

La revisión técnica cubre la publicación de cosechas (HU-02), el catálogo con filtros (HU-04), el registro de productores (HU-01), la consulta de perfil (HU-07) y el cálculo de fletes por municipio mediante Strategy.

La evidencia disponible corresponde al código y a la ejecución local de `mvn clean verify` en la rama `feature/HU-02`, el 29 de octubre de 2026. No se registra aquí una aprobación del Product Owner ni se presenta una ejecución de GitHub Actions como evidencia.

## Evidencia técnica

| Criterio | Resultado verificable |
|---|---|
| Publicación de cosecha | `POST /api/v1/productos` valida la entrada, busca al productor y persiste la cosecha. |
| Catálogo | `GET /api/v1/productos` contempla municipio, categoría, ambos filtros y consulta sin filtros. |
| Registro y perfil | El registro valida duplicados y cifra la contraseña; la consulta de perfil transforma a un DTO público. |
| Patrón comportamental GoF | Strategy selecciona la tarifa por municipio mediante `EstrategiaFlete`. Las tarifas configuradas están documentadas como valores de ejemplo pendientes de validar con el Product Owner. |
| Desacoplamiento | Los controladores dependen de interfaces de servicio; las implementaciones reciben sus dependencias por constructor. |
| Pruebas | 27 pruebas ejecutadas; 0 fallos, 0 errores y 0 omitidas. Incluyen Mockito en servicios y MockMvc en controladores. |
| Checkstyle | 0 violaciones en la verificación local. |
| JaCoCo | 95,93% de instrucciones y 100% de ramas en el reporte local. El umbral configurado es 60% para ambos contadores. |
| Build local | `mvn clean verify`: `BUILD SUCCESS`. |
| Integración continua | El workflow ejecuta el job `build` en push a `main`/`develop` y pull request hacia ambas ramas. No se presenta un check remoto verde para esta rama. |
| Protección de `main` | La configuración consultada exige pull request, una aprobación y el check `build`; no permite force-push ni eliminación de la rama. |

## Artefactos de diseño

Se incluyen el diagrama de despliegue, los diagramas de secuencia y componentes, y mockups de registro, perfil y catálogo. El PDF de mockups contiene tres páginas. La validación de usabilidad por parte del Product Owner sigue pendiente.

## Pendientes para cerrar la revisión

1. Validar las tarifas de flete con el Product Owner.
2. Obtener la aprobación del Product Owner sobre los criterios funcionales y los mockups.
3. Integrar los cambios a `develop` y registrar el resultado verde de GitHub Actions.
4. Completar la configuración del tablero público y registrar su evidencia visual.

Este documento describe resultados técnicos comprobables; no afirma que haya ocurrido una demostración en vivo ni asigna decisiones a personas que no constan en la evidencia.
