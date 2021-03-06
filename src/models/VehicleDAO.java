package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/** 
 * Class to connect and retrieve data from vehicle database
 * @author Oliver Heib
 */
public class VehicleDAO {
	private Connection c;
	private Statement s;
	private ResultSet r;
	
	private Connection getConnection() {
		Connection dbConnection = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println(e.getMessage());
		}
		try
		{
			String dbURL = "jdbc:sqlite:vehicles.sqlite";         
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection;
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}
	
	private void closeConnection()
	{
		try 
		{
			if (r != null) 
			{
			r.close();
			}
			if (s != null) 
			{
				s.close();
			}
			if (c != null) 
			{
				c.close();
			}
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	
	/** 
	 * Retrieve all vehicles from vehicle database
	 * @return ArrayList array of all Vehicles
	 */
	public ArrayList<Vehicle> getAllVehicle()
	{
		c = null;
		s = null;
		r = null;
		ArrayList<Vehicle> a = new ArrayList<Vehicle>() ;
		String query ="SELECT * FROM vehicles";
		
		try
		{
			c = getConnection();
			s = c.createStatement();
			r = s.executeQuery(query);
			
			while (r.next())
			{
				a.add(new Vehicle(r.getInt("vehicle_id"), r.getString("make"), r.getString("model"), r.getInt("year"), r.getInt("price"), r.getString("license_number"), r.getString("colour"), r.getInt("number_doors"), r.getString("transmission"), r.getInt("mileage"), r.getString("fuel_type"), r.getInt("engine_size"), r.getString("body_style"), r.getString("condition"), r.getString("notes")));
			}
			return a;
			
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());	
			return a;
		}
		finally
		{
			closeConnection();
		}
	}
	
	/** 
	 * Retrieve a vehicle from vehicle database with a specified ID
	 * @return ArrayList array of a Vehicles
	 * @param criteria ID of vehicle requested
	 */
	public ArrayList<Vehicle> getVehicle(int criteria)
	{
		c = null;
		s = null;
		r = null;
		ArrayList<Vehicle> a = new ArrayList<Vehicle>();
		String query ="SELECT * FROM vehicles WHERE vehicle_id LIKE "+criteria;
		
		try
		{
			c = getConnection();
			s = c.createStatement();
			r = s.executeQuery(query);
			
			while (r.next())
			{
				a.add(new Vehicle(r.getInt("vehicle_id"), r.getString("make"), r.getString("model"), r.getInt("year"), r.getInt("price"), r.getString("license_number"), r.getString("colour"), r.getInt("number_doors"), r.getString("transmission"), r.getInt("mileage"), r.getString("fuel_type"), r.getInt("engine_size"), r.getString("body_style"), r.getString("condition"), r.getString("notes")));
			}
			return a;
			
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());	
			return a;
		}
		finally
		{
			closeConnection();
		}
		
	}
	
	/** 
	 * Retrieve vehicles from vehicle database where specified field like specified string criteria 
	 * @return ArrayList array of Vehicles matching search criteria
	 * @param field Field of criteria should be checked against
	 * @param criteria Criteria to check against
	 */
	public ArrayList<Vehicle> SearchVehicleString(String field, String criteria)
	{
		c = null;
		s = null;
		r = null;
		ArrayList<Vehicle> a = new ArrayList<Vehicle>() ;
		String query ="SELECT * FROM vehicles WHERE "+field+" LIKE '%"+criteria+"%'";
		
		try
		{
			c = getConnection();
			s = c.createStatement();
			r = s.executeQuery(query);
			
			while (r.next())
			{
				a.add(new Vehicle(r.getInt("vehicle_id"), r.getString("make"), r.getString("model"), r.getInt("year"), r.getInt("price"), r.getString("license_number"), r.getString("colour"), r.getInt("number_doors"), r.getString("transmission"), r.getInt("mileage"), r.getString("fuel_type"), r.getInt("engine_size"), r.getString("body_style"), r.getString("condition"), r.getString("notes")));
			}
			return a;
			
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());	
			return a;
		}
		finally
		{
			closeConnection();
		}
		
	}
	
	/** 
	 * Retrieve vehicles from vehicle database where specified field like specified integer criteria 
	 * @return ArrayList array of Vehicles matching search criteria
	 * @param field Field of criteria should be checked against
	 * @param criteria Criteria to check against
	 */
	public ArrayList<Vehicle> SearchVehicleInt(String field, int criteria)
	{
		c = null;
		s = null;
		r = null;
		ArrayList<Vehicle> a = new ArrayList<Vehicle>();
		String query ="SELECT * FROM vehicles WHERE "+field+" LIKE "+criteria;
		
		try
		{
			c = getConnection();
			s = c.createStatement();
			r = s.executeQuery(query);
			
			while (r.next())
			{
				a.add(new Vehicle(r.getInt("vehicle_id"), r.getString("make"), r.getString("model"), r.getInt("year"), r.getInt("price"), r.getString("license_number"), r.getString("colour"), r.getInt("number_doors"), r.getString("transmission"), r.getInt("mileage"), r.getString("fuel_type"), r.getInt("engine_size"), r.getString("body_style"), r.getString("condition"), r.getString("notes")));
			}
			return a;
			
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());	
			return a;
		}
		finally
		{
			closeConnection();
		}
		
	}
	
	/** 
	 * Retrieve vehicles from vehicle database where specified field between specified integer criteria 
	 * @return ArrayList array of Vehicles matching search criteria
	 * @param field Field of criteria should be checked against
	 * @param criteria1 Criteria to check against
	 * @param criteria2 Criteria to check against
	 */
	public ArrayList<Vehicle> SearchVehicleRange(String field, int criteria1, int criteria2)
	{
		c = null;
		s = null;
		r = null;
		ArrayList<Vehicle> a = new ArrayList<Vehicle>();
		String query ="SELECT * FROM vehicles WHERE "+field+" LIKE "+criteria1+" and "+ criteria2;
		
		try
		{
			c = getConnection();
			s = c.createStatement();
			r = s.executeQuery(query);
			
			while (r.next())
			{
				a.add(new Vehicle(r.getInt("vehicle_id"), r.getString("make"), r.getString("model"), r.getInt("year"), r.getInt("price"), r.getString("license_number"), r.getString("colour"), r.getInt("number_doors"), r.getString("transmission"), r.getInt("mileage"), r.getString("fuel_type"), r.getInt("engine_size"), r.getString("body_style"), r.getString("condition"), r.getString("notes")));
			}
			return a;
			
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());	
			return a;
		}
		finally
		{
			closeConnection();
		}
	}
	
	/** 
	 * Insert a new vehicle into the database
	 * @return boolean whether the insert was successful
	 * @param newVehicle The vehicle to be inserted into the database
	 */
	public boolean InsertVehicle(Vehicle newVehicle)
	{
		String query ="SELECT MAX(vehicle_id) FROM vehicles";
		int id = 0;
		String query2 = null;
		try
		{
			c = getConnection();
			s = c.createStatement();
			r = s.executeQuery(query);
			
			
			while (r.next())
			{
				id = r.getInt("MAX(vehicle_id)");
			}
			newVehicle.setVehicle_id(id + 1);
			
			query2 ="INSERT INTO vehicles (vehicle_id, make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, notes) Values ("+newVehicle.getVehicle_id()+", '"+newVehicle.getMake()+"', '"+newVehicle.getModel()+"', "+newVehicle.getYear()+", "+newVehicle.getPrice()+", '"+newVehicle.getLicense_number()+"', '"+newVehicle.getColour()+"', "+newVehicle.getNumber_doors()+", '"+newVehicle.getTransmission()+"', "+newVehicle.getMileage()+", '"+newVehicle.getFuel_type()+"', "+newVehicle.getEngine_size()+", '"+newVehicle.getBody_style()+"', '"+newVehicle.getCondition()+"', '"+newVehicle.getNotes()+"')";
			

			
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());	
			
		}
		finally
		{
			
		}
		
		try
		{
			c = getConnection();
			s = c.createStatement();
			s.executeUpdate(query2);
			s.close();
			return true;
			
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());	
			return false;
		}
		finally
		{
			closeConnection();
		}
		
	}
	
	/** 
	 * Updates vehicles from vehicle database
	 * @return boolean Whether search was successful
	 * @param upVehicle The vehicle with values to update
	 */
	public boolean UpdateVehicle(Vehicle upVehicle)
	{
		try
		{
			String query2 ="UPDATE vehicles SET make =\""+upVehicle.getMake()+"\", model =\""+upVehicle.getModel()+"\", year ="+upVehicle.getYear()+", price ="+upVehicle.getPrice()+", license_number =\""+upVehicle.getLicense_number()+"\", colour =\""+upVehicle.getColour()+"\", number_doors ="+upVehicle.getNumber_doors()+", transmission =\""+upVehicle.getTransmission()+"\", mileage ="+upVehicle.getMileage()+", fuel_type =\""+upVehicle.getFuel_type()+"\", engine_size ="+upVehicle.getEngine_size()+", body_style =\""+upVehicle.getBody_style()+"\", condition =\""+upVehicle.getCondition()+"\", notes =\""+upVehicle.getNotes()+"\" WHERE vehicle_id = "+upVehicle.getVehicle_id();
			
			c = getConnection();
			s = c.createStatement();
			s.executeUpdate(query2);
			s.close();
			return true;
			
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());	
			return false;
		}
		finally
		{
			closeConnection();
		}
	}
	
	/** 
	 * Deletes vehicles from vehicle database with specified ID
	 * @return boolean Whether deletion was successful
	 * @param ID of vehicle to delete
	 */
	public boolean DeleteVehicle(int ID)
	{
		try
		{
			String query2 ="DELETE FROM vehicles WHERE vehicle_id = "+ID;
			c = getConnection();
			s = c.createStatement();
			s.executeUpdate(query2);
			s.close();
			return true;
			
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());	
			return false;
		}
		finally
		{
			closeConnection();
		}
	}
}
