package edu.pucmm;


import edu.pucmm.exception.DuplicateEmployeeException;
import edu.pucmm.exception.EmployeeNotFoundException;
import edu.pucmm.exception.InvalidSalaryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author me@fredpena.dev
 * @created 02/06/2024  - 00:47
 */

public class EmployeeManagerTest {

    private EmployeeManager employeeManager;
    private Position juniorDeveloper;
    private Position seniorDeveloper;
    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    public void setUp() {
        employeeManager = new EmployeeManager();
        juniorDeveloper = new Position("1", "Junior Developer", 30000, 50000);
        seniorDeveloper = new Position("2", "Senior Developer", 60000, 90000);
        employee1 = new Employee("1", "John Doe", juniorDeveloper, 40000);
        employee2 = new Employee("2", "Jane Smith", seniorDeveloper, 70000);
        employeeManager.addEmployee(employee1);
    }

    @Test
    public void testAddEmployee() {
        // TODO: Agregar employee2 al employeeManager y verificar que se agregó correctamente.
        employeeManager.addEmployee(employee2);

        // - Verificar que el número total de empleados ahora es 2.
        // - Verificar que employee2 está en la lista de empleados.
        //assertTrue(true);

        assertTrue(employeeManager.getEmployees().contains(employee2) && employeeManager.getEmployees().size() == 2);
    }

    @Test
    public void testRemoveEmployee() {
        // TODO: Eliminar employee1 del employeeManager y verificar que se eliminó correctamente.
        // - Agregar employee2 al employeeManager.
        employeeManager.addEmployee(employee2);

        // - Eliminar employee1 del employeeManager.
        employeeManager.removeEmployee(employee1);

        // - Verificar que el número total de empleados ahora es 1.
        // - Verificar que employee1 ya no está en la lista de empleados.
        //assertTrue(true);

        assertTrue(employeeManager.getEmployees().contains(employee2) && employeeManager.getEmployees().size() == 1);
    }

    @Test
    public void testCalculateTotalSalary() {
        // TODO: Agregar employee2 al employeeManager y verificar el cálculo del salario total.
        // - Agregar employee2 al employeeManager.
        employeeManager.addEmployee(employee2);

        // - Verificar que el salario total es la suma de los salarios de employee1 y employee2.
        // assertTrue(true);

        // - El salario total debería ser 40000 + 70000 = 110000.
        assertEquals(110000, employeeManager.calculateTotalSalary());
    }

    @Test
    public void testUpdateEmployeeSalaryValid() {
        // TODO: Actualizar el salario de employee1 a una cantidad válida y verificar la actualización.
        // - Actualizar el salario de employee1 a 45000.
        employeeManager.updateEmployeeSalary(employee1, 45000);

        // - Verificar que el salario de employee1 ahora es 45000.
        // assertTrue(true);
        assertEquals(45000, employee1.getSalary());
    }

    @Test
    public void testUpdateEmployeeSalaryInvalid() {
        // TODO: Intentar actualizar el salario de employee1 a una cantidad inválida y verificar la excepción.
        // - Intentar actualizar el salario de employee1 a 60000 (que está fuera del rango para Junior Developer).
        // - Verificar que se lanza una InvalidSalaryException.
        //assertTrue(true);
        assertThrows(InvalidSalaryException.class, () -> employeeManager.updateEmployeeSalary(employee1, 60000));

    }

    @Test
    public void testUpdateEmployeeSalaryEmployeeNotFound() {
        // TODO: Intentar actualizar el salario de employee2 (no agregado al manager) y verificar la excepción.
        // - Intentar actualizar el salario de employee2 a 70000.
        // - Verificar que se lanza una EmployeeNotFoundException.
        // assertTrue(true);
        assertThrows(EmployeeNotFoundException.class, () -> employeeManager.updateEmployeeSalary(employee2, 70000));
    }

    @Test
    public void testUpdateEmployeePositionValid() {
        // TODO: Actualizar la posición de employee2 a una posición válida y verificar la actualización.
        // - Agregar employee2 al employeeManager.
        employeeManager.addEmployee(employee2);
        // - Actualizar la posición de employee2 a seniorDeveloper.
        employeeManager.updateEmployeePosition(employee2, seniorDeveloper);
        // - Verificar que la posición de employee2 ahora es seniorDeveloper.
        assertEquals(employeeManager.getEmployees().getLast().getPosition(), seniorDeveloper);
    }

    @Test
    public void testUpdateEmployeePositionInvalidDueToSalary() {
        // TODO: Intentar actualizar la posición de employee1 a seniorDeveloper y verificar la excepción.
        // - Intentar actualizar la posición de employee1 a seniorDeveloper.
        // - Verificar que se lanza una InvalidSalaryException porque el salario de employee1 no está dentro del rango para Senior Developer.
        // assertTrue(true);
        assertThrows(InvalidSalaryException.class, () -> employeeManager.updateEmployeePosition(employee1, seniorDeveloper));
    }

    @Test
    public void testUpdateEmployeePositionEmployeeNotFound() {
        // TODO: Intentar actualizar la posición de employee2 (no agregado al manager) y verificar la excepción.
        // - Intentar actualizar la posición de employee2 a juniorDeveloper.
        // - Verificar que se lanza una EmployeeNotFoundException.
        // assertTrue(true);

        assertThrows(EmployeeNotFoundException.class, () -> employeeManager.updateEmployeePosition(employee2, juniorDeveloper));

    }

    @Test
    public void testIsSalaryValidForPosition() {
        // TODO: Verificar la lógica de validación de salario para diferentes posiciones.

        // - Verificar que un salario de 40000 es válido para juniorDeveloper.
        // - Verificar que un salario de 60000 no es válido para juniorDeveloper.
        // - Verificar que un salario de 70000 es válido para seniorDeveloper.
        // - Verificar que un salario de 50000 no es válido para seniorDeveloper.
        // assertTrue(true);


        if(employeeManager.isSalaryValidForPosition(juniorDeveloper, 40000)
        && !employeeManager.isSalaryValidForPosition(juniorDeveloper, 60000)
        && employeeManager.isSalaryValidForPosition(seniorDeveloper, 70000)
        && !employeeManager.isSalaryValidForPosition(seniorDeveloper, 50000)) {

            assertTrue(true);
        } else {
            fail();
        }



    }

    @Test
    public void testAddEmployeeWithInvalidSalary() {
        // TODO: Intentar agregar empleados con salarios inválidos y verificar las excepciones.
        // - Crear un empleado con un salario de 60000 para juniorDeveloper.
        Employee invalidJunior = new Employee("3", "Invalid Junior", juniorDeveloper, 60000);
        assertThrows(InvalidSalaryException.class,
                () -> {
                    employeeManager.addEmployee(invalidJunior);
                }
        );
        // - Verificar que se lanza una InvalidSalaryException al agregar este empleado.
        // - Crear otro empleado con un salario de 40000 para seniorDeveloper.
        Employee invalidSenior = new Employee("4", "Invalid Senior", seniorDeveloper, 40000);
        // - Verificar que se lanza una InvalidSalaryException al agregar este empleado.
        assertThrows(InvalidSalaryException.class,
                () -> {
                    employeeManager.addEmployee(invalidSenior);
                }
        );



    }

    @Test
    public void testRemoveExistentEmployee() {
        // TODO: Eliminar un empleado existente y verificar que no se lanza una excepción.
        // - Eliminar employee1 del employeeManager.
        // - Verificar que no se lanza ninguna excepción.
       //         assertTrue(true);
        assertDoesNotThrow(() -> employeeManager.removeEmployee(employee1));
    }

    @Test
    public void testRemoveNonExistentEmployee() {
        // TODO: Intentar eliminar un empleado no existente y verificar la excepción.
        // - Intentar eliminar employee2 (no agregado al manager).
        // - Verificar que se lanza una EmployeeNotFoundException.
        // assertTrue(true);
        assertThrows(EmployeeNotFoundException.class, () -> employeeManager.removeEmployee(employee2));

    }

    @Test
    public void testAddDuplicateEmployee() {
        // TODO: Intentar agregar un empleado duplicado y verificar la excepción.
        // - Intentar agregar employee1 nuevamente al employeeManager.
        // - Verificar que se lanza una DuplicateEmployeeException.
        //         assertTrue(true);
        assertThrows(DuplicateEmployeeException.class, () -> employeeManager.addEmployee(employee1));
    }

    // El sistema debe permitir validar múltiples rangos de salario con pruebas parametrizadas.
    @ParameterizedTest
    @CsvSource({
            "30000, 40000",
            "40000, 45000",
    })
    public void testIsSalaryValidForPosition_JuniorDeveloper(double low, double high) {
        // Verifica que el salario esté dentro del rango de Junior Developer.
        assertTrue(employeeManager.isSalaryValidForPosition(juniorDeveloper, low) && employeeManager.isSalaryValidForPosition(juniorDeveloper, high));
    }


    @ParameterizedTest
    @CsvSource({
            "60000, 65000",
            "70000, 90000",
    })
    public void testIsSalaryValidForPosition_SeniorDeveloper(double low, double high) {
        // Verifica que el salario esté dentro del rango de Senior Developer.
        assertTrue(employeeManager.isSalaryValidForPosition(seniorDeveloper, low) && employeeManager.isSalaryValidForPosition(seniorDeveloper, high));
    }


}
