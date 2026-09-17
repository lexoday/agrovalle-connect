# Definition of Done — AgroValle Connect

Contrato técnico que garantiza la integridad de cada incremento funcional entregado, alineado con ISO/IEC 25010.

Una Historia de Usuario se considera **"Done"** únicamente cuando se cumplen todos los puntos siguientes:

- [ ] **Build Local:** el proyecto compila sin errores en el entorno local (`mvn clean install`).
- [ ] **Linter Pass (Checkstyle):** cero advertencias de estilo bajo `checkstyle.xml` (Google Java Style).
- [ ] **Functional Correctness:** el 100% de las pruebas unitarias existentes pasan con éxito.
- [ ] **Peer Review:** todo Pull Request fue revisado y aprobado por al menos un compañero antes de fusionarse.
- [ ] **Documentation:** el `README.md` y la documentación de `/docs` están actualizados con los cambios.
- [ ] **Commits:** el historial sigue estrictamente la convención de Conventional Commits.
- [ ] **Automatización:** los hooks de Husky (`.husky/pre-commit`) están activos y bloquean commits que no cumplan linter o pruebas.

---

**Firmado por el equipo:**

| Nombre               | Firma / usuario GitHub    | Fecha          |
| -------------------- | ----------------------    | -------------  |
| Mateo Castro Pedroza | Mateo / valkardraymo      | 16/09/2026     |
| Sebastian Maturana   | Jhor/SebastianMaturana    | 16/09/2026     | 
| Cesar Mosquera       | Cesar/CesarMosquera       | 16/09/2026     |
| Esteban Diaz         | Esteban/estebandi1975-afk | 16/09/2026     |

