package hw3dbms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Populate {
	public static void main(String[] args) throws IOException {
		ConnectDB db = new ConnectDB();
		Connection con = db.getConnection();
		BufferedReader textreader = null;
		String cline;

//movie
		ArrayList<Movie> m1 = new ArrayList<>();
		try {
			textreader = new BufferedReader(new FileReader("Dat/movies.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			while ((cline = textreader.readLine()) != null) {
				try {
					String ar1[] = cline.split("\\t");
					if (ar1.length > 1) {
						int movie_id = Integer.parseInt(ar1[0]);
						String title=ar1[1];
						int year =Integer.parseInt(ar1[5]);
						float ar=0;
						if(!ar1[7].equals("\\N")) {ar =Float.parseFloat(ar1[7]);} 
						int arn=0;
						if(!ar1[8].equals("\\N")) {arn =Integer.parseInt(ar1[8]);} 
						float tcr=0;
						if(!ar1[12].equals("\\N")) { tcr =Float.parseFloat(ar1[12]);}
						int tcrn=0;
						if(!ar1[13].equals("\\N")) { tcrn =Integer.parseInt(ar1[13]);}
						float aur=0;
						if(!ar1[17].equals("\\N")) { aur =Float.parseFloat(ar1[17]);}
						int aurn =0;
						if(!ar1[18].equals("\\N")) {aurn =Integer.parseInt(ar1[18]);}
						Movie temp = new Movie(movie_id, title,year,ar,arn,tcr,tcrn,aur,aurn);
						m1.add(temp);
					}
				} catch (NumberFormatException E) {
					//E.printStackTrace();
					continue;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// clearing the previous table
				try {
					System.out.println("movie  - clearing table!");
					Statement stmt = con.createStatement();
					String sql = "DELETE FROM MOVIES";
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					System.out.println("movie  - failed clearing!");
					e.printStackTrace();
				}
				// entering the data
				try {
					System.out.println("Enter Movie ");
					String sql = "INSERT INTO MOVIES" + "(MOVIEID,TITLE,YEAR,AR,NR,TCR,TCRN,AUR,AURN)" + "VALUES (?,?,?,?,?,?,?,?,?)";
					PreparedStatement pstmt = con.prepareStatement(sql);
					for (Movie m : m1) {
						pstmt.setInt(1, m.movie_id);
						pstmt.setString(2, m.title);
						pstmt.setInt(3, m.year);
						pstmt.setFloat(4, m.ar);
						pstmt.setInt(5, m.arn);
						pstmt.setFloat(6, m.tcr);
						pstmt.setInt(7, m.tcrn);
						pstmt.setFloat(8, m.aur);
						pstmt.setInt(9, m.aurn);
						pstmt.executeQuery();
					}
					System.out.println("movie - inserted!");
				} catch (SQLException e) {
					System.out.println("movie - failed inserting!");
					e.printStackTrace();
				}
		
		// genre
		ArrayList<Moviegenre> mg = new ArrayList<>();
		try {
			textreader = new BufferedReader(new FileReader("Dat/movie_genres.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			while ((cline = textreader.readLine()) != null) {
				try {
					String ar[] = cline.split("\\t");
					if (ar.length > 1) {
						int movie_id = Integer.parseInt(ar[0]);
						String genre = ar[1];
						Moviegenre temp = new Moviegenre(movie_id, genre);
						mg.add(temp);
					}
				} catch (NumberFormatException E) {
					continue;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// clearing the previous table
		try {
			System.out.println("movie genre - clearing table!");
			Statement stmt = con.createStatement();
			String sql = "DELETE FROM GENRES";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("movie genre - failed clearing!");
			e.printStackTrace();
		}
		// entering the data
		try {
			System.out.println("Enter Movie genre");
			String sql = "INSERT INTO GENRES" + "(MOVIEID, GENRE)" + "VALUES (?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			for (Moviegenre m : mg) {
				pstmt.setInt(1, m.movie_id);
				pstmt.setString(2, m.genre);
				pstmt.executeQuery();
			}
			System.out.println("genre - inserted!");
		} catch (SQLException e) {
			System.out.println("genre - failed inserting!");
			e.printStackTrace();
		}

		// country
		ArrayList<MovieCountry> mc = new ArrayList<>();
		try {
			textreader = new BufferedReader(new FileReader("Dat/movie_countries.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			while ((cline = textreader.readLine()) != null) {
				try {
					String ar[] = cline.split("\\t");
					if (ar.length > 1) {
						int movie_id = Integer.parseInt(ar[0]);
						String country = ar[1];
						MovieCountry temp = new MovieCountry(movie_id, country);
						mc.add(temp);
					}
				} catch (NumberFormatException E) {
					continue;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// clearing the previous table
		try {
			System.out.println("movie country - deleting table");
			Statement stmt = con.createStatement();
			String sql = "DELETE FROM MOVIE_COUNTRY";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("movie country - failed to delete");
			e.printStackTrace();
		}
		// entering the data
		try {
			System.out.println("Enter Movie country");
			String sql = "INSERT INTO MOVIE_COUNTRY" + "(MOVIEID, COUNTRY)" + "VALUES (?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			for (MovieCountry m : mc) {
				pstmt.setInt(1, m.movie_id);
				pstmt.setString(2, m.country);
				pstmt.executeQuery();
			}
			System.out.println("country  inserted");
		} catch (SQLException e) {
			System.out.println("country  failed inserting");
			e.printStackTrace();
		}
		
		// movie tag
				ArrayList<MovieTagged> mt = new ArrayList<>();
				try {
					textreader = new BufferedReader(new FileReader("Dat/movie_tags.dat"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					while ((cline = textreader.readLine()) != null) {
						try {
							String ar[] = cline.split("\\t");
							if (ar.length > 1) {
								int movie_id = Integer.parseInt(ar[0]);
								int tag_id =  Integer.parseInt(ar[1]);
								int tagW =  Integer.parseInt(ar[2]);
								MovieTagged temp = new MovieTagged(movie_id, tag_id,tagW);
								mt.add(temp);
							}
						} catch (NumberFormatException E) {
							continue;
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// clearing the previous table
				try {
					System.out.println("movie tagged - clearing table!");
					Statement stmt = con.createStatement();
					String sql ="DELETE FROM MOVIETAGGED";
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					System.out.println("movie tagged - failed clearing!");
					e.printStackTrace();
				}
				// entering the data
				try {
					System.out.println("Enter Movie tagged");
					String sql = "INSERT INTO MOVIETAGGED" + "(MOVIEID,TAGID,TAGW)" + "VALUES (?,?,?)";
					PreparedStatement pstmt = con.prepareStatement(sql);
					for (MovieTagged m : mt) {
						pstmt.setInt(1, m.movie_id);
						pstmt.setInt(2, m.tag_id);
						pstmt.setInt(3, m.tagW);
						pstmt.executeQuery();
					}
					System.out.println("Movie tagged - inserted!");
				} catch (SQLException e) {
					System.out.println("Movie tagged - failed inserting!");
					e.printStackTrace();
				}
				
				// tag value
				ArrayList<TagValue> tv = new ArrayList<>();
				try {
					textreader = new BufferedReader(new FileReader("Dat/tags.dat"));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					while ((cline = textreader.readLine()) != null) {
						try {
							String ar[] = cline.split("\\t");
							if (ar.length > 1) {
								int tag_id =  Integer.parseInt(ar[0]);
								String  tagvalue= ar[1];
								TagValue temp = new TagValue(tag_id,tagvalue);
								tv.add(temp);
							}
						} catch (NumberFormatException E) {
//							E.printStackTrace();
							continue;
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// clearing the previous table
				try {
					System.out.println(" tag value- clearing table!");
					Statement stmt = con.createStatement();
					String sql = "DELETE FROM tag value";
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					System.out.println(" tag- failed clearing!");
					e.printStackTrace();
				}
				// entering the data
				try {
					System.out.println("Enter tag");
					String sql = "INSERT INTO TAG" + "(TAGID,TAGVALUE)" + "VALUES (?,?)";
					PreparedStatement pstmt = con.prepareStatement(sql);
					for (TagValue m : tv) {
						pstmt.setInt(1, m.tag_id);
						pstmt.setString(2, m.tagvalue);
						pstmt.executeQuery();
					}
					System.out.println("Tag- inserted!");
				} catch (SQLException e) {
					System.out.println("Tag- failed inserting!");
					e.printStackTrace();
				}

		// location
		ArrayList<MovieLocation> loc = new ArrayList<>();
		try {
			textreader = new BufferedReader(new FileReader("Dat/movie_locations.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			while ((cline = textreader.readLine()) != null) {
				try {
					String ar[] = cline.split("\\t");
					if (ar.length > 1 && !(ar[1].isEmpty())) {
						int movie_id = Integer.parseInt(ar[0]);
						String loc1 = ar[1];
						MovieLocation temp = new MovieLocation(movie_id, loc1);
						loc.add(temp);
					}
				} catch (NumberFormatException E) {
					//E.printStackTrace();
					continue;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// clearing the previous table
		try {
			System.out.println("movie location - deleting table");
			Statement stmt = con.createStatement();
			String sql = "DELETE FROM MOVIE_LOCATION";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("movie location - failed to delete");
			e.printStackTrace();
		}
		// entering the data
		try {
			System.out.println("Enter Movie location");
			String sql = "INSERT INTO MOVIE_LOCATION" + "(MOVIEID,LOC1)" + "VALUES (?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			for (MovieLocation m : loc) {
				pstmt.setInt(1, m.movie_id);
				pstmt.setString(2, m.loc1);
				pstmt.executeQuery();
			}
			System.out.println("location  inserted done");
		} catch (SQLException e) {
			System.out.println("location  failed inserting");
			e.printStackTrace();
		}
	}
}
