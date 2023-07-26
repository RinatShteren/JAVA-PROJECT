
import java.awt.*;
import java.awt.event.*;
/**
 *Department responsible for opening the game
 * @author Rinat Shteren
 */

public class Open extends Window{
private Panel p1; // Button panel
private Button[] b; // Button array
private Image img; // Background image
int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width; // Screen width
int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height; // Screen height
	/**
	 * constructor
	 */
	public Open()
	{
		super(App.f);
		this.setBounds(0, 0, screenWidth, screenHeight);
		this.setBackground(Color.white);
		this.initPic();
	    this.setVisible(true);
	    this.p1=new Panel ();
	    this.p1.setBackground(Color.blue);
	    this.p1.setLayout(new GridLayout(1,3,0,0));
	    this.b=new Button[3];
	    for(int i=0;i<3;i++)
	    	{
			     this.b[i]=new Button("");
			     this.b[i].setBackground(Color.blue);
			     this.b[i].setVisible(false);
			     this.b[i].addActionListener(new Caftorim());
			     this.p1.add(this.b[i]);
			     this.b[i].setFont(new Font("Arial",Font.BOLD,50));
	    	}
	    
	    this.b[1].setLabel(" Instructions ");
	    this.b[1].setVisible(true);
	    this.b[2].setLabel(" start ");
	    this.b[2].setVisible(true);
	    this.b[0].setLabel(" exit ");
	    this.b[0].setVisible(true);
	    this.add(this.p1,"South");
	    this.setVisible(true);
	    
	    }


	/**
	 * Action responsible for drawing to the screen
	 * @param g-graphic configuration
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
		public void paint(Graphics g)
		{
		g.drawImage(this.img,0,0,screenWidth,screenHeight,this);
		
	     g.setFont(new Font("Arial",Font.BOLD,150));
	     g.drawString("dimonds on the" , 100, 300);
	     g.setFont(new Font("Arial",Font.BOLD,300));
	     g.setColor(Color.red);
	     g.drawString("sea" , 300, 500);
	    /**
	     * init image
	     */
		}
		public void initPic()
		{
			this.img =this.createImage(screenWidth,screenHeight);
			
			this.img =this.getToolkit().getImage("C:/Users/USER/Desktop/Documents/vs_code_project_dimond/myProject/src/blue5.jpg");
		}
		
		

/**
 *  The internal department handles the pressing of buttons
 */
		public class Caftorim implements ActionListener
		  {
			/**
			 * An action that allows moving screens by clicking
			 */
		     public void actionPerformed(ActionEvent e)
		     {
		            Button b=(Button)e.getSource();
		            Instructions h;
		            Game l;
		          
		             
		            if (b.equals(Open.this.b[1]))
		             h=new Instructions();
		            if (b.equals(Open.this.b[2]))
		            	l=new Game();
		            if (b.equals(Open.this.b[0]))
		        	    System.exit(0);
		       
		     }
		  }
}





 