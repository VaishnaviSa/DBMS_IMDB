package hw3dbms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class HW3 extends JFrame {

	private JFrame frame;
	private ArrayList<JCheckBox>  genre_CheckBoxes=new ArrayList<JCheckBox>();
	private ArrayList<JCheckBox>  country_CheckBoxes=new ArrayList<JCheckBox>();
	private ArrayList<JCheckBox>  loc_CheckBoxes=new ArrayList<JCheckBox>();
	private ArrayList<JCheckBox>  tag_CheckBoxes=new ArrayList<JCheckBox>();
	
	private ArrayList<String>  genrelist=new ArrayList<String>();
	private ArrayList<String>  countrylist=new ArrayList<String>();
	private ArrayList<String>  loclist=new ArrayList<String>();
	private ArrayList<String>  taglist=new ArrayList<String>();
	
	private JPanel contentPane,countryPanel,locationPanel,tagPanel;
	private JComboBox<String> selectandor,rateComp,nrComp,tw;
	private JTextField tfrate,tfNumrate,tagWt;
	private JTextField yearFrom=new JTextField ("",4);
	private JTextField yearTo=new  JTextField("",4);
	private JTextArea showQuery,showresult;
	private JButton execute,tagsButton;
	Pulldata pd=new Pulldata();
	GetAction ga=new GetAction();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HW3 frame = new HW3();
					frame.setVisible(true);
					frame.addWindowListener(new WindowAdapter(){
						@Override
						public void windowClosing(WindowEvent E) {
							ConnectDB.close_connectDB();
							System.out.print("Close connection");
						}
						});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		////System.runFinalizersOnExit(true);
	}

	/**
	 * Create the application
	 */
	public HW3() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(300, 20, 950, 700);
		contentPane= new JPanel();
		contentPane.setOpaque(true);
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel_m = new JLabel("MOVIES");
		lblNewLabel_m.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_m.setBounds(0, 0, 500, 40);
		lblNewLabel_m.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNewLabel_m);
		
		JLabel lblNewLabel = new JLabel("Genre");
		lblNewLabel.setBounds(1, 50, 170, 31);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(new Color(255,255,204));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Country");
		lblNewLabel_2.setBounds(170, 50, 170, 31);
		contentPane.setOpaque(true);
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setBackground(new Color(255,255,204));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Filming Location");
		lblNewLabel_3.setBounds(340, 50, 170, 31);
		contentPane.setOpaque(true);
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBackground(new Color(255,255,204));
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_3);
		
		
		JPanel panel_2=new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(520,20,100,10);
		panel_2.setOpaque(true);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_5 = new JLabel("Critics rating");
		lblNewLabel_5.setBounds(520, 50, 170, 30);
		contentPane.setOpaque(true);
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setBackground(new Color(255,255,204));
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_5);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_t1 = new JLabel("Movie Tag Values");
		lblNewLabel_t1.setBounds(690, 50, 170, 31);
		contentPane.setOpaque(true);
		lblNewLabel_t1.setForeground(Color.BLACK);
		lblNewLabel_t1.setBackground(new Color(255,255,204));
		lblNewLabel_t1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_t1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_t1);
		
		JLabel lblNewLabel_6 = new JLabel("rating");
		lblNewLabel_6.setBounds(520, 100, 170, 30);
		contentPane.setOpaque(true);
		lblNewLabel_6.setForeground(Color.BLACK);
		lblNewLabel_6.setBackground(new Color(255,255,204));
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblNewLabel_6);
		contentPane.add(lblNewLabel_6);
		
		rateComp=new JComboBox<String>();
		rateComp.setBounds(560,100,72,27);
		rateComp.setModel(new DefaultComboBoxModel<String>(new String[]{"<=",">=","<",">","="}));
		panel_2.add(rateComp);
		contentPane.add(rateComp);
		
		JLabel lblNewLabel_v = new JLabel("values");
		lblNewLabel_v.setBounds(520, 150, 170, 30);
		contentPane.setOpaque(true);
		lblNewLabel_v.setForeground(Color.BLACK);
		lblNewLabel_v.setBackground(new Color(255,255,204));
		lblNewLabel_v.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_v.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblNewLabel_v);
		contentPane.add(lblNewLabel_v);
		
		tfrate=new JTextField();
		tfrate.setBounds(560,150,72,27);
		panel_2.add(tfrate);
		contentPane.add(tfrate);
		tfrate.setColumns(8);
		
		JLabel lblNewLabel_t = new JLabel("Number of Reviews");
		lblNewLabel_t.setBounds(520, 180, 170, 30);
		contentPane.setOpaque(true);
		lblNewLabel_t.setForeground(Color.BLACK);
		lblNewLabel_t.setBackground(new Color(255,255,204));
		lblNewLabel_t.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_t.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblNewLabel_t);
		contentPane.add(lblNewLabel_t);
		
		nrComp=new JComboBox<String>();
		nrComp.setBounds(560,210,72,27);
		nrComp.setModel(new DefaultComboBoxModel<String>(new String[]{"<=",">=","<",">","="}));
		panel_2.add(nrComp);
		contentPane.add(nrComp);

		JLabel lblNewLabel_v2 = new JLabel("values");
		lblNewLabel_v2.setBounds(520, 250, 170, 30);
		contentPane.setOpaque(true);
		lblNewLabel_v2.setForeground(Color.BLACK);
		lblNewLabel_v2.setBackground(new Color(255,255,204));
		lblNewLabel_v2.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_v2.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblNewLabel_v2);
		contentPane.add(lblNewLabel_v2);
		
		tfNumrate=new JTextField();
		tfNumrate.setBounds(560,250,72,27);
		panel_2.add(tfNumrate);
		contentPane.add(tfNumrate);
		tfNumrate.setColumns(8);
		
		JLabel lblNewLabel_y = new JLabel("MOVIE YEAR");
		lblNewLabel_y.setBounds(520,310, 170, 30);
		contentPane.setOpaque(true);
		lblNewLabel_y.setForeground(Color.BLACK);
		lblNewLabel_y.setBackground(new Color(255,255,204));
		lblNewLabel_y.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_y.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblNewLabel_y);
		contentPane.add(lblNewLabel_y);
		
		JLabel lblNewLabel_4 = new JLabel("Search Between");
		lblNewLabel_4.setBounds(0, 430, 170, 30);
		contentPane.setOpaque(true);
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setBackground(new Color(255,255,204));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_4);
		
		selectandor =new JComboBox<String>();
		selectandor.setModel(new DefaultComboBoxModel<String>(new String[]{"AND","OR"}));
		selectandor.setBounds(200,440,113,26);
		selectandor.addActionListener(ga);
		contentPane.add(selectandor);
		
		JLabel lblNewLabel_r = new JLabel("Results");
		lblNewLabel_r.setBounds(520, 430, 170, 30);
		contentPane.setOpaque(true);
		lblNewLabel_r.setForeground(Color.BLACK);
		lblNewLabel_r.setBackground(new Color(255,255,204));
		lblNewLabel_r.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_r.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_r);
		
		//genre panel
		JPanel genrePanel = new JPanel();
		genrePanel.setLayout(new GridLayout(30,0,0,0));
		ArrayList<String> genrepull=pd.getGenre();
		int i=0;
		for(String s: genrepull) {
			JCheckBox checkBox=new JCheckBox(s);
			checkBox.setBounds(19,20+i,129,23);
			checkBox.addActionListener(ga);
			genrePanel.add(checkBox);
			genre_CheckBoxes.add(checkBox);
			i=i+25;
		}
		genre_CheckBoxes.get(1).setSelected(true);
		JScrollPane scroll_genre= new JScrollPane();
		scroll_genre.setViewportView(genrePanel);
		scroll_genre.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_genre.setBounds(0, 90,170,340);
		contentPane.add(scroll_genre);
		
		
		//country panel
		countryPanel = new JPanel();
		countryPanel.setLayout(new GridLayout(80,0,0,0));
		String sgenre= genre_CheckBoxes.get(1).getText();
		genrelist.add(sgenre);
		ArrayList<String> countries=pd.getCountry(genrelist,"AND");
		System.out.print(countries.size());
		i=0;
		for(String c: countries) {
			JCheckBox checkBox=new JCheckBox(c);
			checkBox.setBounds(19,20+i,129,23);
			checkBox.addActionListener(ga);
			countryPanel.add(checkBox);
			country_CheckBoxes.add(checkBox);
			i=i+25;
		}
		//country_CheckBoxes.get(1).setSelected(true);
		JScrollPane scroll_country= new JScrollPane();
		scroll_country.setViewportView(countryPanel);
		scroll_country.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_country.setBounds(172, 90,170,340);
		contentPane.add(scroll_country);
		
		
		//filming location panel
		        locationPanel = new JPanel();
		        locationPanel.setLayout(new GridLayout(120,0,0,0));
		       // String sgenre1= genre_CheckBoxes.get(1).getText();
				//genrelist.add(sgenre1);
				ArrayList<String> locpull=pd.getFilmLocation(genrelist,"AND");
				System.out.print(locpull.size());
				i=0;
				for(String l: locpull) {
					JCheckBox checkBox=new JCheckBox(l);
					checkBox.setBounds(19,20+i,129,23);
					checkBox.addActionListener(ga);
					locationPanel.add(checkBox);
					loc_CheckBoxes.add(checkBox);
					i=i+25;
				}
				//loc_CheckBoxes.get(1).setSelected(true);
				JScrollPane scroll_loc= new JScrollPane();
				scroll_loc.setViewportView(locationPanel);
				scroll_loc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scroll_loc.setBounds(342, 90,170,340);
				contentPane.add(scroll_loc);
				
				
				//movie tag panel
				tagPanel = new JPanel();
				tagPanel.setLayout(new GridLayout(80,0,0,0));
				//ArrayList<String> tagpull=pd.getTags("AND");
			/*	System.out.print(tagpull.size());
				i=0;
				for(String c: tagpull) {
					JCheckBox checkBox=new JCheckBox(c);
					checkBox.setBounds(19,20+i,129,23);
					checkBox.addActionListener(ga);
					tagPanel.add(checkBox);
					tag_CheckBoxes.add(checkBox);
					i=i+25;
				}*/
				JScrollPane scroll_tag= new JScrollPane();
				scroll_tag.setViewportView(tagPanel);
				scroll_tag.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scroll_tag.setBounds(700, 90,200,250);
				contentPane.add(scroll_tag);
				
				showQuery = new JTextArea();
				showQuery.setLayout(new GridLayout(1,0,0,0));
				showQuery.setLineWrap(true);
				showQuery.setWrapStyleWord(true);
				
				JScrollPane queryscrollPane = new JScrollPane();
				queryscrollPane.setViewportView(showQuery);
				queryscrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				queryscrollPane.setBounds(5,480,350,200);
				contentPane.add(queryscrollPane);
				queryscrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

				// result area starts here
				showresult = new JTextArea();
				showresult.setLayout(new GridLayout(1,0,0,0));
				showresult.setLineWrap(true);
				showresult.setWrapStyleWord(true);

				JScrollPane resultscrollPane = new JScrollPane();
				resultscrollPane.setViewportView(showresult);
				resultscrollPane.setBounds(520,480,300,200);
				contentPane.add(resultscrollPane);
				resultscrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				resultscrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				//execute button is here
				execute = new JButton("Execute Query");
				execute.setBounds(360, 500, 117, 29);
				contentPane.add(execute);
				execute.addActionListener(ga);
				
				tagsButton = new JButton("Click Tag Value");
				tagsButton.setBounds(360, 600, 117, 29);
				contentPane.add(tagsButton);
				tagsButton.addActionListener(ga);
				
				
				
				JLabel lblNewLabel_yf = new JLabel("FROM");
				lblNewLabel_yf.setBounds(520, 350, 170,15);
				contentPane.setOpaque(true);
				lblNewLabel_yf.setForeground(Color.BLACK);
				lblNewLabel_yf.setBackground(new Color(255,255,204));
				lblNewLabel_yf.setFont(new Font("Arial", Font.BOLD, 12));
				lblNewLabel_yf.setHorizontalAlignment(SwingConstants.LEFT);
				panel_2.add(lblNewLabel_yf);
				contentPane.add(lblNewLabel_yf);
				
				yearFrom.setBounds(560,350,72,27);
				panel_2.add(yearFrom);
				contentPane.add(yearFrom);
				yearFrom.setColumns(8);
				
				JLabel lblNewLabel_yf1 = new JLabel("TO");
				lblNewLabel_yf1.setBounds(520, 400, 170,15);
				contentPane.setOpaque(true);
				lblNewLabel_yf1.setForeground(Color.BLACK);
				lblNewLabel_yf1.setBackground(new Color(255,255,204));
				lblNewLabel_yf1.setFont(new Font("Arial", Font.BOLD, 12));
				lblNewLabel_yf1.setHorizontalAlignment(SwingConstants.LEFT);
				panel_2.add(lblNewLabel_yf1);
				contentPane.add(lblNewLabel_yf1);

				yearTo.setBounds(560,400,72,27);
				panel_2.add(yearTo);
				contentPane.add(yearTo);
				yearTo.setColumns(8);
				
				
				JLabel lblNewLabel_tw = new JLabel("Tag Weights");
				lblNewLabel_tw.setBounds(690, 350, 170, 30);
				contentPane.setOpaque(true);
				lblNewLabel_tw.setForeground(Color.BLACK);
				lblNewLabel_tw.setBackground(new Color(255,255,204));
				lblNewLabel_tw.setFont(new Font("Arial", Font.BOLD, 12));
				lblNewLabel_tw.setHorizontalAlignment(SwingConstants.LEFT);
				panel_2.add(lblNewLabel_tw);
				contentPane.add(lblNewLabel_tw);
				
				tw=new JComboBox<String>();
				tw.setBounds(770,350,72,27);
				tw.setModel(new DefaultComboBoxModel<String>(new String[]{"<=",">=","<",">","="}));
				panel_2.add(tw);
				contentPane.add(tw);

				JLabel lblNewLabel_tw1 = new JLabel("Values");
				lblNewLabel_tw1.setBounds(690, 400, 170, 30);
				contentPane.setOpaque(true);
				lblNewLabel_tw1.setForeground(Color.BLACK);
				lblNewLabel_tw1.setBackground(new Color(255,255,204));
				lblNewLabel_tw1.setFont(new Font("Arial", Font.BOLD, 12));
				lblNewLabel_tw1.setHorizontalAlignment(SwingConstants.LEFT);
				panel_2.add(lblNewLabel_tw1);
				contentPane.add(lblNewLabel_tw1);
				
				tagWt=new JTextField();
				tagWt.setBounds(770,400,72,27);
				panel_2.add(tagWt);
				contentPane.add(tagWt);
				tagWt.setColumns(8);
				
		}
		
	class GetAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			if(genre_CheckBoxes.contains(event.getSource()) || event.getSource()==selectandor) {
				System.out.println("actionPerformed Genre");
				genrelist.clear();
				for(JCheckBox c:genre_CheckBoxes) {
					if(c.isSelected())
						genrelist.add(c.getText());
				}
				for(String g: genrelist) {
					System.out.println(g);
				}
				String andor=(String)selectandor.getSelectedItem();
				ArrayList<String>  countries=pd.getCountry(genrelist,andor);
				int i=0;
				countryPanel.removeAll();
				country_CheckBoxes.clear();
				for(String c: countries) {
					JCheckBox checkBox=new JCheckBox(c);
					checkBox.setBounds(19,20+i,129,23);
					checkBox.addActionListener(ga);
					countryPanel.add(checkBox);
					country_CheckBoxes.add(checkBox);
					i=i+25;
				}
				System.out.println("countries action performed");
				
				
				ArrayList<String>  locpull=pd.getFilmLocation(genrelist,andor);
				int k=0;
				locationPanel.removeAll();
				loc_CheckBoxes.clear();
				
				for(String v: locpull) {
					JCheckBox checkBox=new JCheckBox(v);
					checkBox.setBounds(19,20+k,129,23);
					checkBox.addActionListener(ga);
					locationPanel.add(checkBox);
					loc_CheckBoxes.add(checkBox);
					k=k+25;
				}
				System.out.println("location action performed");
				
				/*ArrayList<String>  tagpull=pd.getTagsT(genrelist,andor);
				int z=0;
				tagPanel.removeAll();
			    tag_CheckBoxes.clear();
				
				for(String v: tagpull) {
					JCheckBox checkBox=new JCheckBox(v);
					checkBox.setBounds(19,20+k,129,23);
					checkBox.addActionListener(ga);
					tagPanel.add(checkBox);
					tag_CheckBoxes.add(checkBox);
					z=z+25;
				}
				System.out.println("tag value action performed");*/
				repaint();
				revalidate();	
			}
			countrylist.clear();
			loclist.clear();
			//taglist.clear();
			for(JCheckBox cb:country_CheckBoxes) {
				if(cb.isSelected())
					countrylist.add(cb.getText());
			}
			for(JCheckBox cb2:loc_CheckBoxes) {
				if(cb2.isSelected())
					loclist.add(cb2.getText());
			}
			
			/*for(JCheckBox cb2:tag_CheckBoxes) {
				if(cb2.isSelected())
					taglist.add(cb2.getText());
			}*/
			
			if(event.getSource()==tagsButton) {
				
				int numRev=0;
				double rValue=0;
				int  yearF=0;
				int  yearT=0;
				String attrS ="OR";
				String rateCompS="=";
				String nrCompS="=";
				String twS="=";
				int tagWeight=0;
				if(!tagWt.getText().equals("")) {
					tagWeight=Integer.parseInt(tagWt.getText());
				}
				if(!tfrate.getText().equals("")) {
					rValue=Double.parseDouble(tfrate.getText());
				}
				if(!tfNumrate.getText().equals("")) {
					numRev=Integer.parseInt(tfNumrate.getText());
				}
				
				if(!yearFrom.getText().equals("") ) {
					if(Integer.parseInt(yearFrom.getText()) >1900 && Integer.parseInt(yearFrom.getText()) <2018)
					yearF=Integer.parseInt(yearFrom.getText());
				}
				
				if(!yearTo.getText().equals("")){
						if(Integer.parseInt(yearTo.getText()) > 1900 && Integer.parseInt(yearTo.getText()) < 2018)
					yearT=Integer.parseInt(yearTo.getText());
				}
				String attrS1=(String)selectandor.getSelectedItem();
				rateCompS=(String)rateComp.getSelectedItem();
				nrCompS=(String)nrComp.getSelectedItem();
				twS=(String)tw.getSelectedItem();
				
				GenreateQuery q=new GenreateQuery();
				String tagGenerate=q.createQueryTags(attrS1,countrylist,genrelist,loclist,rValue,numRev,rateCompS,nrCompS,yearF,yearT,tagWeight,twS);	
			
				if(!tagGenerate.equals("")) {
					
			ArrayList<String>  tagpull=pd.getResultTag(tagGenerate);
			int z=0;
			tagPanel.removeAll();
		    tag_CheckBoxes.clear();
			
			for(String v: tagpull) {
				JCheckBox checkBox=new JCheckBox(v);
				checkBox.setBounds(19,20+z,129,23);
				checkBox.addActionListener(ga);
				tagPanel.add(checkBox);
				tag_CheckBoxes.add(checkBox);
				z=z+25;
			}
			System.out.println("tag value action performed");
				}
			repaint();
			revalidate();
			}
			taglist.clear();
			for(JCheckBox cb2:tag_CheckBoxes) {
				if(cb2.isSelected())
					taglist.add(cb2.getText());
			}
			
			
			//execute button
			if(event.getSource()==execute) {
				int numRev=0;
				double rValue=0;
				int  yearF=0;
				int  yearT=0;
				String attrS ="OR";
				String rateCompS="=";
				String nrCompS="=";
				String twS="=";
				int tagWeight=0;
				if(!tagWt.getText().equals("")) {
					tagWeight=Integer.parseInt(tagWt.getText());
				}
				if(!tfrate.getText().equals("")) {
					rValue=Double.parseDouble(tfrate.getText());
				}
				if(!tfNumrate.getText().equals("")) {
					numRev=Integer.parseInt(tfNumrate.getText());
				}
				
				if(!yearFrom.getText().equals("") ) {
					if(Integer.parseInt(yearFrom.getText()) >1900 && Integer.parseInt(yearFrom.getText()) <2018)
					yearF=Integer.parseInt(yearFrom.getText());
				}
				
				if(!yearTo.getText().equals("")){
						if(Integer.parseInt(yearTo.getText()) > 1900 && Integer.parseInt(yearTo.getText()) < 2018)
					yearT=Integer.parseInt(yearTo.getText());
				}
				attrS=(String)selectandor.getSelectedItem();
				rateCompS=(String)rateComp.getSelectedItem();
				nrCompS=(String)nrComp.getSelectedItem();
				twS=(String)tw.getSelectedItem();
				
				GenreateQuery q=new GenreateQuery();
				String sqlq=q.createQuery(attrS,countrylist,genrelist,loclist,taglist,rValue,numRev,rateCompS,nrCompS,yearF,yearT,tagWeight,twS);
				
				if(sqlq.equals(""))
				{
				showQuery.setText("Nothing is selected");
				showresult.setText("No results to show");
				}
			else{
				showQuery.setText(sqlq);
				ArrayList<Result> result= pd.getResult(sqlq);
				System.out.print(result.size());
				if(result.size()==0)
					{
					showresult.setText("No movie match this criteria");
					}
				else{
					showresult.setText("");
					for(int i=0;i<result.size();i++)
						{String line="TITLE: "+result.get(i).title+"\n COUNTRY: "+result.get(i).country+"\n YEAR: "+result.get(i).year+"\n RATING: "+result.get(i).rating+"\n REVIEW: "+result.get(i).reviews+"\n Genre:";
						for(int j=0;j<result.get(i).genreName.size();j++) {
							line+=result.get(i).genreName.get(j)+", ";
						}
						line+="\n Film Locations";
						for(int j=0;j<result.get(i).loc1.size();j++) {
							line+=result.get(i).loc1.get(j)+", ";
						}
						showresult.append("MOVIE-"+result.get(i).id+"\n"+line+"\n\n\n");
						}}
				}
			}			
			repaint();
			revalidate();
		}
		
	}
}