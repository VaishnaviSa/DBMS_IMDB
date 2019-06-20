package hw3dbms;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Pulldata {
	 private static Connection  con;
	 private static ConnectDB  db;
	 private static Statement st,st2,st3,st4;
	 Pulldata(){
		 try{
			db=new ConnectDB();
			con=db.getConnection();
			st=con.createStatement(); 
			st2=con.createStatement();
			st3=con.createStatement();
			st4=con.createStatement();
		 }catch(SQLException e) {}
		 finally{
			 //db.close_connectDB();
		 }
	 }
	 
	 public ArrayList<String> getGenre(){
		 ArrayList<String> gl=new  ArrayList<String>();
		 try {
			 String genre1=null;
			 ResultSet rs=st.executeQuery("SELECT DISTINCT G.GENRE FROM GENRES G ORDER BY G.GENRE");
			 System.out.println("genre pull data success");
			 while(rs.next()) {
				 genre1=rs.getString("GENRE");
				 gl.add(genre1);
			 }
		 }catch(SQLException e) {
			 System.out.println("genre pull data failure");
			 e.printStackTrace();
			 
		 }
		return gl;
	 }
	 
	 //getCOUNTRY
	 public ArrayList<String> getCountry(ArrayList<String> genre,String andOr){
	 ArrayList<String> country=new  ArrayList<String>();
	 if(genre.size()==0){
		 return country;
	 }
	 String pulldata;
	 if(andOr.equals("AND"))
		 pulldata="INTERSECT";
	 else
	   pulldata="UNION";
	 try{
		 System.out.println("pull countries from table");
         String country_1;
         int i=0;
         String c_query="SELECT DISTINCT COUNTRY FROM MOVIE_COUNTRY M,GENRES G WHERE M.MOVIEID=G.MOVIEID  AND G.GENRE='"+genre.get(i)+"'";         
         for(i=i+1;i<genre.size();i++) {
        	 c_query+=" "+pulldata+" SELECT DISTINCT COUNTRY FROM MOVIE_COUNTRY M,GENRES G WHERE M.MOVIEID=G.MOVIEID  AND G.GENRE='"+genre.get(i)+"'";
         }
         ResultSet rs=st.executeQuery(c_query);
		 System.out.println("country pull data success");
		 while(rs.next()) {
			 country_1=rs.getString("COUNTRY");
			 country.add(country_1);
		 }
		 System.out.println(country.size());
	 }catch(SQLException e) {
		 System.out.println("COUNTRY pull data failure");
		 e.printStackTrace();
		 
	 }
	return country;
 }
	 //getLocation
	 public ArrayList<String> getFilmLocation(ArrayList<String> genre,String andOr){
		 ArrayList<String> location=new  ArrayList<String>();
		 if(genre.size()==0){
			 return location;
		 }
		 String pulldata;
		 if(andOr.equals("AND"))
			 pulldata="INTERSECT";
		 else
		   pulldata="UNION";
		 try{
			 System.out.println("pull location from table");
	         String loc_1;
	         int i=0;
	         String c_query="SELECT DISTINCT LOC1 FROM MOVIE_LOCATION L,GENRES G,MOVIE_COUNTRY C WHERE L.MOVIEID=G.MOVIEID AND C.COUNTRY=L.LOC1 AND G.GENRE='"+genre.get(i)+"'";           
	         for(i=i+1;i<genre.size();i++) {
	        	 c_query+=" "+pulldata+" SELECT DISTINCT LOC1 FROM MOVIE_LOCATION L,GENRES G,MOVIE_COUNTRY C WHERE L.MOVIEID=G.MOVIEID AND C.COUNTRY=L.LOC1 AND   G.GENRE='"+genre.get(i)+"'";   
	         }
	         ResultSet rs=st.executeQuery(c_query);
			 System.out.println("filmlocation pull data success");
			 while(rs.next()) {
				 loc_1=rs.getString("LOC1");
				 location.add(loc_1);
			 }
			 System.out.println(location.size());
		 }catch(SQLException e) {
			 System.out.println("filmlocation pull data failure");
			 e.printStackTrace();
			 
		 }
		return location;
	 }
	 
	 //
	 
	 
	 public ArrayList<String> getResultTag(String query){
		 ArrayList<String> resultTag= new ArrayList<>();
		 try{
				System.out.println("query");
			//	String temp= "";
				ResultSet rs3 = st.executeQuery(query);
				while(rs3.next()){
					String temp=rs3.getString("TAGVALUE");
					resultTag.add(temp);
				}
			}
			catch (SQLException e) {
				System.out.println("failed to acquire tag");
				e.printStackTrace();
			}
		 return resultTag;
		}
	 
	 
		public ArrayList<Result> getResult(String query){
			 ArrayList<Result> result= new ArrayList<>();
			 try{
					System.out.println(query);
					ResultSet rs = st.executeQuery(query);
					while(rs.next()){
						Result temp= new Result();
						temp.id=rs.getInt("MOVIEID");
						temp.title=rs.getString("TITLE");
						temp.year=rs.getInt("YEAR");
						temp.reviews=rs.getInt("REVIEWS");
						temp.rating=rs.getFloat("AVGRATING");
						
						try{
							ResultSet rs2 = st2.executeQuery("SELECT C.COUNTRY FROM MOVIE_COUNTRY C, MOVIES M WHERE M.MOVIEID=C.MOVIEID AND M.MOVIEID='"+temp.id+"'");
							while(rs2.next()){
								temp.country=rs2.getString("COUNTRY");
								
							}
						}

						catch (SQLException e) {
							System.out.println("failed to acquire country");
							e.printStackTrace();
							continue;
						}
						try{
							ResultSet rs2 = st2.executeQuery("SELECT G.GENRE FROM GENRES G, MOVIES M WHERE M.MOVIEID=G.MOVIEID AND M.MOVIEID='"+temp.id+"'");
							while(rs2.next()){
								String name = rs2.getString("GENRE");
								temp.genreName.add(name);
							}
						}

						catch (SQLException e) {
							System.out.println("failed to acquire genre");
							e.printStackTrace();
							continue;
						}
						
						try{
							ResultSet rs4 = st4.executeQuery("SELECT DISTINCT L.LOC1 FROM MOVIE_LOCATION L , MOVIES M WHERE M.MOVIEID=L.MOVIEID AND M.MOVIEID='"+temp.id+"'");
							while(rs4.next()){
								String name = rs4.getString("LOC1");
								temp.loc1.add(name);
							}
						}

						catch (SQLException e) {
							System.out.println("failed to acquire location");
							e.printStackTrace();
							continue;
						}
						
						result.add(temp);
					}
				}
				catch (SQLException e) {
					System.out.println("failed to acquire result");
					e.printStackTrace();
				}
			 return result;
			}
		
	 }