import java.awt.*;
import java.awt.event.*;


/**
 * Department responsible for game instructions
 * @author Rinat Shteren
 */
public class Instructions extends Window
{
	private Panel p1; // Button panel
private Button[] b; // Button array
private Image img; // Background image
int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width; // Screen width
int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height; // Screen height
	/**
	 * constructor
	 */
	public Instructions()
	{
	super(App.f);
	this.setBounds(0,0,screenWidth,screenHeight);
	this.setBackground(Color.white);
	this.initPic();
    this.setVisible(true);
    this.p1=new Panel ();//button panel
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
    
    this.b[0].setLabel(" return ");
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
	
	 g.setColor(Color.red);
     g.setFont(new Font("Arial",Font.BOLD,100));
     g.drawString("Instructions" , 900, 200);
     g.setFont(new Font("Arial",Font.BOLD,23));

     g.drawString("Diamonds in the Sea is a perfect game for you!", 10, 250);
	 g.drawString("Help Ariel find the diamonds!", 10, 350);
	 g.drawString("How to play..? ", 10, 400);
	 g.drawString("In the game, you will see diamonds in different colors and shapes.", 10, 450);
	 g.drawString("Your goal is to score as many points as possible by swapping diamonds.", 10, 500);
	 g.drawString("You can swap any diamond with another diamond that is adjacent to it from the top, bottom, right, or left.", 10, 550);
	 g.drawString("However, the swap will only be made if it creates a sequence of at least 3 diamonds or more, of the same color.", 10, 600);
	 g.drawString("The more diamonds you group together, the more points you will score.", 10, 650);
	 g.drawString("If you have 3 or more diamonds of the same color in a row, they will disappear and new diamonds will fall from the top of the screen.", 10, 700);
	 g.drawString("If you can't make any more swaps, the game will end.", 10, 750);
	 g.drawString("The player with the highest score at the end of the game wins!", 10, 800);
    
    
	}
	/**
     * init image
     */
	public void initPic()
	{
		this.img =this.createImage(screenWidth,screenHeight);
		
		this.img =this.getToolkit().getImage("C:/Users/USER/Desktop/Documents/vs_code_project_dimond/myProject/src/blue5.jpg");
	}
	/**
	 * The internal department handles the pressing of buttons
	 */
	public class Caftorim implements ActionListener
	  {
		/**
		 * An action that allows moving screens by clicking
		 */
	     public void actionPerformed(ActionEvent e)
	     {
	            Button b=(Button)e.getSource(); // the button that cliked
	            Open l;
	            if (b.equals(Instructions.this.b[0]))
	            	l=new Open();
	       
	     }
	  }
}
