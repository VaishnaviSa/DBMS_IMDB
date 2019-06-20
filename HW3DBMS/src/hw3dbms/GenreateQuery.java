package hw3dbms;

import java.util.ArrayList;

public class GenreateQuery {
	public String createQuery(String attrS,ArrayList<String> countrylist,ArrayList<String> genrelist,ArrayList<String> loclist,ArrayList<String> taglist,double 
			rValue,int numRev,String rateCompS,String nrCompS,int yearF,int yearT,int tagWeight,String twS) {
		ArrayList<String> as=new ArrayList<>();
		int i=0;
		int count=0;
		String inner="",option;
		if(attrS.equals("OR")) {
			option="UNION";
		}
		else
			option="INTERSECT";
		if(genrelist.size()!=0) {
		i=0;
		String temp="";
		temp+="(select movieid from genres where genre='"+genrelist.get(i)+"'"; 
		for(i=1;i<genrelist.size();i++) {
			temp+=" "+attrS+" genre='"+genrelist.get(i)+"'";
		}
		temp+=")";
		as.add(temp);
		}
		
		//countries
		
		if(countrylist.size()!=0) {
			i=0;
			String temp="";
			temp+="(select movieid from MOVIE_COUNTRY where country='"+countrylist.get(i)+"'"; 
			for(i=1;i<countrylist.size();i++) {
				temp+=" "+attrS+" country='"+countrylist.get(i)+"'"; 
			}
			temp+=")";
			as.add(temp);
		}
		
		
		if(loclist.size()!=0) {
			i=0;
			String temp="";
			temp+="(select movieid from MOVIE_LOCATION where loc1='"+loclist.get(i)+"'"; 
			for(i=1;i<loclist.size();i++) {
				temp+=" "+attrS+" LOC1='"+loclist.get(i)+"'"; 
			}
			temp+=")";
			as.add(temp);
		}
		
		if(taglist.size()!=0) {
			i=0;
			String temp="";
			temp+="(select movieid from MOVIETAGGED H,TAG T where H.TAGID=T.TAGID AND T.TAGVALUE='"+taglist.get(i)+"'"; 
			for(i=1;i<taglist.size();i++) {
				temp+=" "+attrS+" tagvalue='"+taglist.get(i)+"'"; 
			}
			temp+=")";
			as.add(temp);
		}
		
		//rating 
		String option1="INTERSECT";
		count=0;
		String mtemp="";
		if(rValue!=0) {
			mtemp+="select movieid from movies where AR "+ rateCompS+ rValue;
			as.add(mtemp);
	}
	
	String ttemp3="";
	if(numRev!=0) {
		ttemp3+="select movieid from movies where NR "+ nrCompS+ numRev;
			as.add(ttemp3);
	}
	
	String ttemp2="";
	if(yearF!=0) {
		ttemp2+="select movieid from movies where year > "+ yearF;
			as.add(ttemp2);
	}
	
	String ttemp1="";
	if(yearT!=0) {
			ttemp1+="select movieid from movies where year <"+ yearT;
			as.add(ttemp1);
		}
	
		String ttemp="";
		if(tagWeight!=0) {
				ttemp+="select movieid from MOVIETAGGED where TAGW "+ twS+ tagWeight;
				as.add(ttemp);
		}
		
		
		i=0;
		if(as.size()==0)
			return inner;
		
		inner+=as.get(i);
		
		for(i=1;i<as.size();i++) {
			inner+=" "+option1+" ";
			inner+=as.get(i);
		}
		
		String outer= "SELECT m.movieid,m.title,m.year,(m.nr+m.tcrn+m.aurn) as REVIEWS,"
				+" round((AR+TCR+AUR)/3,1) as AVGRATING "+" from movies m" 
				+" WHERE m.movieid in ("+inner+")  order by m.movieid";
		System.out.println(outer);
		return outer;
	}
	
	public String createQueryTags(String attrS,ArrayList<String> countrylist,ArrayList<String> genrelist,ArrayList<String> loclist,double 
			rValue,int numRev,String rateCompS,String nrCompS,int yearF,int yearT,int tagWeight,String twS) {
		ArrayList<String> as=new ArrayList<>();
		int i=0;
		int count=0;
		String inner="",option;
		if(attrS.equals("OR")) {
			option="UNION";
		}
		else
			option="INTERSECT";
		if(genrelist.size()!=0) {
		i=0;
		String temp="";
		temp+="(select movieid from genres where genre='"+genrelist.get(i)+"'"; 
		for(i=1;i<genrelist.size();i++) {
			temp+=" "+attrS+" genre='"+genrelist.get(i)+"'";
		}
		temp+=")";
		as.add(temp);
		}
		
		//countries
		
		if(countrylist.size()!=0) {
			i=0;
			String temp="";
			temp+="(select movieid from MOVIE_COUNTRY where country='"+countrylist.get(i)+"'"; 
			for(i=1;i<countrylist.size();i++) {
				temp+=" "+attrS+" country='"+countrylist.get(i)+"'"; 
			}
			temp+=")";
			as.add(temp);
		}
		
		
		if(loclist.size()!=0) {
			i=0;
			String temp="";
			temp+="(select movieid from MOVIE_LOCATION where loc1='"+loclist.get(i)+"'"; 
			for(i=1;i<loclist.size();i++) {
				temp+=" "+attrS+" LOC1='"+loclist.get(i)+"'"; 
			}
			temp+=")";
			as.add(temp);
		}
		
		
		//rating 
		String option1="INTERSECT";
		count=0;
		String mtemp="";
		if(rValue!=0) {
				mtemp+="select movieid from movies where AR "+ rateCompS+ rValue;
				as.add(mtemp);
		}
		
		String ttemp3="";
		if(numRev!=0) {
			ttemp3+="select movieid from movies where NR "+ nrCompS+ numRev;
				as.add(ttemp3);
		}
		
		String ttemp2="";
		if(yearF!=0) {
			ttemp2+="select movieid from movies where year > "+ yearF;
				as.add(ttemp2);
		}
		
		String ttemp1="";
		if(yearT!=0) {
				ttemp1+="select movieid from movies where year <"+ yearT;
				as.add(ttemp1);
			}
		
		
		String ttemp="";
		if(tagWeight!=0) {
				ttemp+="select movieid from MOVIETAGGED where TAGW "+ twS+ tagWeight;
				as.add(ttemp);
		}
		
		
		i=0;
		if(as.size()==0)
			return inner;
		
		inner+=as.get(i);
		
		for(i=1;i<as.size();i++) {
			inner+=" "+option1+" ";
			inner+=as.get(i);
		}
		
		String outer= "select DISTINCT T.TAGVALUE FROM TAG T,MOVIETAGGED D "
				+" WHERE  T.TAGID=D.TAGID AND  D.movieid in ("+inner+") order by T.TAGVALUE";
		System.out.println(outer);
		return outer;
	}
}
