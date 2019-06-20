package hw3dbms;

public class Movie {
	int movie_id;
	String title;
	int  year;
	float ar;
	int arn;
	float tcr;
	int tcrn;
	float aur;
	int aurn;
	Movie(int movie_id,String title,int year,float ar,int arn,float tcr,int tcrn,float aur,int aurn){
		this.movie_id=movie_id;
		this.title=title;
		this.year=year;
		this.ar=ar;
		this.arn=arn;
		this.tcr=tcr;
		this.tcrn=tcrn;
		this.aur=aur;
		this.aurn=aurn;
	}
}
