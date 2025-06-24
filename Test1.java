
public class Test1 {
    public static void main(String[] args) {
        GestorEstudiantes gestor = new GestorEstudiantes();
        
        gestor.insertarEstudiante(20, "Jose Salas");
        gestor.insertarEstudiante(5, "Maria Flores");
        gestor.insertarEstudiante(16, "Rosa Nuñes");
        gestor.insertarEstudiante(31, "Carlos Galdos");
        gestor.insertarEstudiante(2, "Luis Meza");
        gestor.insertarEstudiante(16, "Lucas Franco");
        gestor.insertarEstudiante(16, "Ana Lopez");
        gestor.insertarEstudiante(45, "Sofia Cardenas");
        gestor.insertarEstudiante(23, "Patty Alarcon");
        gestor.insertarEstudiante(30, "Pablo Vergara");
        //gestor.reporteEstudiante();
        
        gestor. insertarEstudiante(103, "Ana");
        gestor. insertarEstudiante(110, "Luis");
        gestor.insertarEstudiante(101, "Carlos");
        gestor.insertarEstudiante(120, "Lucía");
        gestor.insertarEstudiante(115, "David");
        gestor. insertarEstudiante(125,"Jorge");
        gestor.insertarEstudiante(140, "Camila");
        gestor. insertarEstudiante(108, "Rosa");
        gestor.insertarEstudiante(132, "Ernesto");
        gestor. insertarEstudiante(128, "Denis");
        gestor. insertarEstudiante(145,"Enrique");
        gestor. insertarEstudiante(122, "Karina");
        gestor.insertarEstudiante(108, "Juan");
        //gestor.reporteEstudiante();
        
        gestor.buscarEstudiante(115);
        gestor.buscarEstudiante(132);
        gestor.buscarEstudiante(199);
    }
}