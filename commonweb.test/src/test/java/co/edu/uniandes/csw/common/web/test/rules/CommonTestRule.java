package co.edu.uniandes.csw.common.web.test.rules;

import java.lang.reflect.Field;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;

public class CommonTestRule implements MethodRule {

     //Arreglo que guarda los objetos de prueba
    private final Object[] params = {1,2,3,4,5} ;
    //Nombre de la variable a la cual esta regla va a asignar el dato que se esté probando.
    private final String caseTestName;
   
    public CommonTestRule(String caseTestName) {
        //Al instanciar esta regla, se generan los datos de prueba con podam. En este caso, se generan 5.
        
       
        for (int i = 0; i < params.length; i++) {
            
            params[i] = (i+1); 
        }
        //se declara que el nombre de la variable que se debe utilizar en el archivo de prueba
        this.caseTestName = caseTestName;
    }
    // Se verifica que la regla se encuentre dentro del mismo paquete que la prueba que lo implementa
    private boolean isInContainer() {
        Exception e = new Exception();
        e.fillInStackTrace();
        return e.getStackTrace()[e.getStackTrace().length - 1].getClassName().equals("java.lang.Thread");
    }
    //Este método contiene las intrucciones para validar la regla.
    public org.junit.runners.model.Statement apply(final org.junit.runners.model.Statement stmnt, FrameworkMethod fm, final Object ob) {
        return new org.junit.runners.model.Statement() {
           //La evaluación de la regla se da por cada prueba con @test del archivo que utiliza esta regla.
            public void evaluate() throws Throwable {
                System.out.println("rule - before " + ob.hashCode());
                //Se indica que cualquier prueba del archivo que utilizae esa regla se ejecute con los datos de prueba inicializados en el constructor de esta clase.
                // Cada dato se coloca en la variable 'dataSample' del archivo que utiliza esta prueba.
                //if (isInContainer()) {
                    for (int i = 0; i < params.length; i++) {
                        Object param = params[i];
                        Field targetField = ob.getClass().getDeclaredField(caseTestName);
                        if (!targetField.isAccessible()) {
                            targetField.setAccessible(true);
                        }
                        targetField.set(ob, param);
                        stmnt.evaluate();
                    }
                //} else {
                    //Ejecuta el test del archivo (
                    stmnt.evaluate();
                //}
            }
        };
    } 
}
