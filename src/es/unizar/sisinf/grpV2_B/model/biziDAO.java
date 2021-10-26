package es.unizar.sisinf.grpV2_B.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import es.unizar.sisinf.grpV2_B.db.*;

public class biziDAO {

    private static String ltiasEstaciones = "SELECT * FROM BiziStation ORDER BY id ASC";
}


// Dado número de estacion -> nbicis disponibles (API)
// Lista de todas las estaciones

// Introducir nueva estacion en la DB