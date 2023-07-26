
import java.awt.*;
import java.awt.event.*;

/**
 * A class that manages the game
 * @author Rinat Shteren
 */
public class Game extends Window
{
		
private Button []b; // screen button
private Panel p; // panel on which the buttons are placed
private Shape3D[][] shapes; // 2D array of all shapes
private int line1, col1; // row and column of the first click
private int line2, col2; // row and column of the second click
private int push; // whether the first or second click, which shape to move
private int [][] status; // matrix representing the board state
private Image img; // background image
private Image img2; // victory image
private int points; // variable representing the player's score
int screenWidth = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width; // screen width
int screenHeight = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height; // screen height
	/**
	 *constructor
	 */
	public Game()
	{
		super(App.f);
		this.setBounds(0, 0, screenWidth, screenHeight);
		this.setBackground(Color.white);
		this.initStutus();
		this.push=1;
		this.addMouseListener(new MyMouse());
		this.shapes();    
		this.initPic();
		this.setVisible(true);
		
        while (Game.this.threeHorizonal() || 
			   Game.this.threeVertical()); 
		this.points=0;
        
        this.p=new Panel ();//button panel
	    this.p.setBackground(Color.blue);
	    this.p.setLayout(new GridLayout(1,3,0,0));
	    this.b=new Button[3];
	    for(int i=0;i<3;i++)
	     {
	     this.b[i]=new Button("");
	     this.b[i].setBackground(Color.blue);
	     this.b[i].setVisible(false);
	     this.b[i].addActionListener(new Caftorim());
	     this.p.add(this.b[i]);
	     this.b[i].setFont(new Font("Arial",Font.BOLD,50));
	     }
	    
	    this.b[1].setLabel("new game");
	    this.b[1].setVisible(true);
	    this.b[0].setLabel(" exit ");
	    this.b[0].setVisible(true);
	    this.add(this.p,"South");
	    this.setVisible(true);
	
	}
    /**
	 * The inner class handles button clicks.
	 */
	public class Caftorim implements ActionListener
	  {
		/*
		 * An action that allows screen switching by clicking.
		 */
		public void actionPerformed(ActionEvent e)
	     {
	            Button b=(Button)e.getSource(); 
	            Game l;
	     
	            if (b.equals(Game.this.b[1]))
	            {
	            	l=new Game();
	            }
	            if (b.equals(Game.this.b[0]))
	        	    System.exit(0);
	     }
	  }

/**
 * Initialize the array representing the board state.
 */
	public void initStutus()
	{
		this.status=new int[8][8];
		for(int i=0;i<8;i++)
		 	 for(int j=0;j<8;j++)
		 	 {
			 	 this.status[i][j]=(int)(Math.random()*5);
			 	 System.out.println(this.status[i][j]);
			 }
	}
/**
 * Initialize the shapes.
 */
	public void shapes()
	{
		
		this.shapes=new Shape3D[8][8];
		for(int i=0;i<8;i++)
			  for(int j=0;j<8;j++)
			  
				 {if(this.status[i][j]==0)
					 this.shapes[i][j]=this.init6TH(i,j);/*init6TH*/
				  if(this.status[i][j]==1)
					 this.shapes[i][j]=this.init1TH(i,j);/*init1TH*/
				  if(this.status[i][j]==2)
					 this.shapes[i][j]=this.initMeroba(i,j);/*initMeroba*/
				  if(this.status[i][j]==3)
					 this.shapes[i][j]=this.initDIMOND(i,j);/*initDIMOND*/
				  if(this.status[i][j]==4)
					 this.shapes[i][j]=this.initSTAR(i,j);/*initSTAR*/
				 }
	
	}
	/**
	 *Initialize the background image.
	 */
	public void initPic()
	{
		this.img =this.createImage(screenWidth,screenHeight);
		this.img =this.getToolkit().getImage("C:/Users/USER/Desktop/Documents/vs_code_project_dimond/myProject/src/blue5.jpg");
		this.img2 =this.createImage(screenWidth,screenHeight);
		this.img2 =this.getToolkit().getImage("C:/Users/USER/Desktop/Documents/vs_code_project_dimond/myProject/src/winner.jpg");
	}
	/**
	 *Check if the shape swap is legal. If it is legal, allow it. Otherwise, do not allow it.
	 */
	public boolean legal()
	{
		return (this.line1==this.line2 && Math.abs(this.col1-this.col2)==1) ||
		(this.col1==this.col2 && Math.abs(this.line1-this.line2)==1);
	}
	/**
	 *Shape swapping
	 */
	public void changeTwo()
	{
		Tran3D t1=new Tran3D();
		Tran3D t2=new Tran3D();
		Graphics g=this.getGraphics();
		if (this.line1==this.line2) //same line
		{
			
			t1.hazaza(20,0, 0);
			t2.hazaza(-20,0, 0);
			for(int i=0;i<4;i++)
			{
				shapes[this.line1][Math.min(this.col1,this.col2)].change(t1);
				shapes[this.line2][Math.max(this.col1,this.col2)].change(t2);
				
				shapes[this.line1][Math.min(this.col1,this.col2)].paintShape(g);
				shapes[this.line2][Math.max(this.col1,this.col2)].paintShape(g);
				this.delay();
			}
			int temp=this.status[this.line1][this.col1];
			this.status[this.line1][this.col1]=this.status[this.line2][this.col2];
			this.status[this.line2][this.col2]=temp;
			
			Shape3D temp1=this.shapes[this.line1][this.col1];
			this.shapes[this.line1][this.col1]=this.shapes[this.line2][this.col2];
			this.shapes[this.line2][this.col2]=temp1;
			
		}
		else
		{
			t1.hazaza(0,20, 0);
			t2.hazaza(0,-20, 0);
			for(int i=0;i<4;i++)
			{
			shapes[Math.min(this.line1,this.line2)][this.col1].change(t1);
			shapes[Math.max(this.line1,this.line2)][this.col2].change(t2);
			shapes[this.line1][Math.min(this.col1,this.col2)].paintShape(g);
			shapes[this.line2][Math.max(this.col1,this.col2)].paintShape(g);
			this.delay();
			}
			int temp=this.status[this.line1][this.col1];
			this.status[this.line1][this.col1]=this.status[this.line2][this.col2];
			this.status[this.line2][this.col2]=temp;
			Shape3D temp1=this.shapes[this.line1][this.col1];
			this.shapes[this.line1][this.col1]=this.shapes[this.line2][this.col2];
			this.shapes[this.line2][this.col2]=temp1;
			
		}
		
	}
	/**
	 *Reduction of balanced shapes
	 */
	public void haktanaHorizonal(int i,int j,int num) 
	{
		Tran3D t=new Tran3D();
		for(int k=0;k<num;k++)
		{	
			t.silumShevet(0.8,0.8, 0.8, 200+(j+k)*80+40, 200+i*80+40,17);
			this.shapes[i][j+k].change(t);
		}
		this.points=this.points+num*13;
	}
	/**
	 *Swapping of balanced shapes
	 */
	
	public void hachlafaHorizonal(int i,int j,int num)
	{
		for(int k=0;k<num;k++)
		{	
			this.status[i][j+k]=(int)(Math.random()*5);
			this.shapes();
			
		}
		this.points=this.points+num*13;
	}
	/**
	 *minimize places
	 */
  
	public void haktanaVertical(int i,int j,int num)
	{
		Tran3D t=new Tran3D();
		for(int k=0;k<num;k++)
		{	
			t.silumShevet(0.8,0.8, 0.8, 200+j*80+40, 200+(i+k)*80+40,17);
			this.shapes[i+k][j].change(t);
		}
		this.points=this.points+num*13;
	}
	/**
	 Vertical shape swap
	 */ 
	public void hachlafaVertical(int i,int j,int num)
	{
		Tran3D t=new Tran3D();
		Shape3D s;
		for(int k=0;k<num;k++)
		{	
			this.status[i+k][j]=(int)(Math.random()*5);
			this.shapes();
		}
		this.points=this.points+num*13;
	}
	/**
	 * Horizontally - when there are three of the same type of diamonds in a row, they are swapped and reduced in size.
	 */ 
	
	public boolean threeHorizonal()
	{
		boolean b=false;
		for(int i=0;i<8;i++)
			for(int j=0;j<6;j++)
			{
				int x=this.status[i][j];
				if (this.status[i][j+1]==x && this.status[i][j+2]==x)
				{
				;
					haktanaHorizonal(i,j,3);
					hachlafaHorizonal(i,j,3);
					this.delay();
					b=true;
				}
			}
		return b;
	}
	/**
	 Horizontally - when there are four diamonds of the same type in a row, they are swapped and reduced in size.
	 */
 
	public boolean fourHorizonal()
	{
		boolean b=false;
		for(int i=0;i<8;i++)
			for(int j=0;j<5;j++)
			{
				int x=this.status[i][j];
				if (this.status[i][j+1]==x && this.status[i][j+2]==x&&this.status[i][j+3]==x)
				{
					
					haktanaHorizonal(i,j,4);
					hachlafaHorizonal(i,j,4);
					this.delay();
					b=true;
				}
			}
		return b;
	}
	/**
	 * Vertically - when there are five diamonds of the same type in a row, they are swapped and reduced in size.
	 */
	public boolean fiveHorizonal()
	{
		boolean b=false;
		for(int i=0;i<8;i++)
			for(int j=0;j<4;j++)
			{
				int x=this.status[i][j];
				if (this.status[i][j+1]==x && this.status[i][j+2]==x&&this.status[i][j+3]==x&&this.status[i][j+4]==x)
				{
					
					haktanaHorizonal(i,j,5);
					hachlafaHorizonal(i,j,5);
					this.delay();
					b=true;
				}
			}
		return b;
	}
/**
 Vertically - when there are three diamonds of the same type in a row, they are swapped and reduced in size.
 */
	public boolean threeVertical()
	{
		boolean b=false;
		for(int j=0;j<8;j++)
			for(int i=0;i<6;i++)
			{
				int x=this.status[i][j];
				if (this.status[i+1][j]==x && this.status[i+2][j]==x)
				{
					
					haktanaVertical(i,j,3);
					hachlafaVertical(i,j,3);
					this.delay();
					b=true;
				}
			}
		return b;
	}
	/**
     *Vertically - when there are four diamonds of the same type in a row, they are swapped and reduced in size.
	 */

	public boolean fourVertical()
	{
		boolean b=false;
		for(int j=0;j<8;j++)
			for(int i=0;i<5;i++)
			{
				int x=this.status[i][j];
				if (this.status[i+1][j]==x && this.status[i+2][j]==x&&this.status[i+3][j]==x)
				{
					
					haktanaVertical(i,j,4);
					hachlafaVertical(i,j,4);
					this.delay();
					b=true;
				}
			}
		return b;
	}
	/**
	 * Vertically - when there are five diamonds of the same type in a row, they are swapped and reduced in size.
	 */

	public boolean fiveVertical()
	{
		boolean b=false;
		for(int j=0;j<8;j++)
			for(int i=0;i<4;i++)
			{
				int x=this.status[i][j];
				if (this.status[i+1][j]==x && this.status[i+2][j]==x&&this.status[i+3][j]==x&&this.status[i+4][j]==x)
				{
					haktanaVertical(i,j,5);
					hachlafaVertical(i,j,5);
					this.delay();
					b=true;
				}
			}
		return b;
	}
	/**
	 * The function that is responsible for drawing the diamonds on setting the score and brightness of the screen
	 */

public void paint(Graphics g)
{
	g.clearRect(0,0, screenWidth,screenHeight);
	g.drawImage(this.img,0,0,screenWidth,screenHeight,this);
	g.setFont(new Font("Arial",Font.BOLD,80));
	g.drawString("score:" , 900, 250);
	g.setFont(new Font("Arial",Font.BOLD,30));
	 g.setColor(Color.pink);
	g.drawString("you shold get 700 to win" , 900, 400);
	
	for(int k=0;k<8;k++)
	{
		  for(int m=0;m<8;m++)
			 {
			  this.shapes[k][m].paintShape(g);
			 }
	}
	g.setFont(new Font("Arial",Font.BOLD,80));
	g.drawString(this.points+" " , 930, 320);
	
	if(this.points>=700)
	{
	 g.setColor(Color.WHITE);
	 g.setFont(new Font("Arial",Font.BOLD,200));
	 g.drawImage(this.img2,0,0,screenWidth,screenHeight,this);
  
	}
}

/**
 * Internal class that handles mouse clicks
 */
public class MyMouse extends MouseAdapter
{
	/**
	 *Action that handles clicks on the game board
	 */
	public void mouseClicked(MouseEvent e)
	{
		Graphics g=Game.this.getGraphics();
		int x =e.getX();
		int y =e.getY();
		if (Game.this.push==1)
		{				
			Game.this.line1=-1;
			Game.this.col1=-1;
			for(int i=0;i<8;i++)
				for(int j=0;j<8;j++)
				{ 
				if (x>200+80*j && x<280+80*j && 
					y>200+80*i && y<280+80*i)
					{Game.this.line1=i;
					Game.this.col1=j; 
					}	
			}
			if(Game.this.line1!=-1 && Game.this.col1!=-1)
			{
				Game.this.shapes[Game.this.line1][Game.this.col1].paintShapeDarker(g);
				Game.this.push=2;
			}
		}
		else //2
		{
			Game.this.line2=-1;
			Game.this.col2=-1;
			for(int i=0;i<8;i++)
				for(int j=0;j<8;j++)
				{ 
				if (x>200+80*j && x<280+80*j && 
					y>200+80*i && y<280+80*i)
				
					{Game.this.line2=i;
					Game.this.col2=j; 
					}
		 }
		if (legal())
		{
				Game.this.shapes[Game.this.line2][Game.this.col2].paintShapeDarker(g);
				Game.this.delay();
				Game.this.changeTwo();
				Game.this.delay();
				Game.this.fiveHorizonal();
				Game.this.fourHorizonal();
				Game.this.threeHorizonal();
				Game.this.fiveVertical();
				Game.this.fourVertical();
				Game.this.threeVertical();
				while (Game.this.threeHorizonal() || 
						   Game.this.threeVertical()); 
		}
			Game.this.paint(g);
			Game.this.push=1;	
	}			
	}
}
/**
 * init STAR
 */

public Shape3D initSTAR(int i,int j)
{
	Vertex3D[] v=new Vertex3D[20];
	
	v[0]=new Vertex3D(240+80*j,210+80*i,5);
	v[1]=new Vertex3D(250+80*j,230+80*i,5);
	v[2]=new Vertex3D(270+80*j,230+80*i,5);
	v[3]=new Vertex3D(260+80*j,250+80*i,5);
	v[4]=new Vertex3D(265+80*j,275+80*i,5);
	v[5]=new Vertex3D(240+80*j,260+80*i,5);
	v[6]=new Vertex3D(215+80*j,275+80*i,5);
	v[7]=new Vertex3D(220+80*j,250+80*i,5);
	v[8]=new Vertex3D(210+80*j,230+80*i,5);
	v[9]=new Vertex3D(230+80*j,230+80*i,5);
	
	v[10]=new Vertex3D(240+80*j,210+80*i,25);
	v[11]=new Vertex3D(250+80*j,230+80*i,25);
	v[12]=new Vertex3D(270+80*j,230+80*i,25);
	v[13]=new Vertex3D(260+80*j,250+80*i,25);
	v[14]=new Vertex3D(265+80*j,275+80*i,25);
	v[15]=new Vertex3D(240+80*j,260+80*i,25);
	v[16]=new Vertex3D(215+80*j,275+80*i,25);
	v[17]=new Vertex3D(220+80*j,250+80*i,25);
	v[18]=new Vertex3D(210+80*j,230+80*i,25);
	v[19]=new Vertex3D(230+80*j,230+80*i,25);
	
	
	Polygon3D[] p=new Polygon3D[12]; 
	Vertex3D[] v0={v[9],v[8],v[7],v[6],v[5],v[4],v[3],v[2],v[1],v[0]}; 
	p[0]=new Polygon3D(v0,Color.pink);
    Vertex3D[] v1={v[0],v[10],v[11],v[1]}; 
	p[1]=new Polygon3D(v1,Color.pink);
	Vertex3D[] v2={v[1],v[2],v[12],v[11]}; 
	p[2]=new Polygon3D(v2,Color.pink);
	Vertex3D[] v3={v[2],v[3],v[13],v[12]}; 
	p[3]=new Polygon3D(v3,Color.pink);
	Vertex3D[] v4={v[3],v[14],v[13],v[3]}; 
	p[4]=new Polygon3D(v4,Color.pink);
	Vertex3D[] v5={v[4],v[14],v[15],v[5]};
	p[5]=new Polygon3D(v5,Color.pink);
	Vertex3D[] v6={v[5],v[15],v[16],v[6]}; 
	p[6]=new Polygon3D(v6,Color.pink);
	Vertex3D[] v7={v[7],v[17],v[16],v[6]}; 
	p[7]=new Polygon3D(v7,Color.pink);
	Vertex3D[] v8={v[8],v[18],v[17],v[7]};
	p[8]=new Polygon3D(v8,Color.pink);
	Vertex3D[] v9={v[9],v[19],v[18],v[8]}; 
	p[9]=new Polygon3D(v9,Color.pink);
	Vertex3D[] v10={v[0],v[10],v[19],v[9]}; 
	p[10]=new Polygon3D(v10,Color.pink);
	Vertex3D[] v11={v[10],v[19],v[18],v[17],v[16],v[15],v[14],v[13],v[12],v[11]}; 
	p[11]=new Polygon3D(v11,Color.pink);
	return new Shape3D(v,p);
}
/**
 * initDIMOND 
 */

public Shape3D initDIMOND(int i,int j)
{
	Vertex3D[] v=new Vertex3D[12];//35
	
	v[0]=new Vertex3D(220+80*j,220+80*i,35);
	v[1]=new Vertex3D(240+80*j,210+80*i,35);
	v[2]=new Vertex3D(260+80*j,220+80*i,35);

	v[3]=new Vertex3D(260+80*j,260+80*i,35);
	v[4]=new Vertex3D(240+80*j,270+80*i,35);
	v[5]=new Vertex3D(220+80*j,260+80*i,35);
	

	v[6]=new Vertex3D(220+80*j,260+80*i,5);
	v[7]=new Vertex3D(220+80*j,220+80*i,5);
	v[8]=new Vertex3D(240+80*j,210+80*i,5);
	
	v[9]=new Vertex3D(260+80*j,220+80*i,5);
	v[10]=new Vertex3D(260+80*j,260+80*i,5);
	v[11]=new Vertex3D(240+80*j,270+80*i,5);
	
	Polygon3D[] p=new Polygon3D[8]; 
	Vertex3D[] v0={v[0],v[1],v[2],v[3],v[4],v[5]}; 
	p[0]=new Polygon3D(v0,Color.orange);
    Vertex3D[] v1={v[0],v[7],v[8],v[1]}; 
	p[1]=new Polygon3D(v1,Color.orange);
	Vertex3D[] v2={v[1],v[8],v[9],v[2]}; 
	p[2]=new Polygon3D(v2,Color.orange);
	Vertex3D[] v3={v[2],v[9],v[10],v[3]}; 
	p[3]=new Polygon3D(v3,Color.orange);
	Vertex3D[] v4={v[3],v[10],v[11],v[4]}; 
	p[4]=new Polygon3D(v4,Color.orange);
	Vertex3D[] v5={v[4],v[5],v[6],v[11]};
	p[5]=new Polygon3D(v5,Color.orange);
	Vertex3D[] v6={v[5],v[6],v[7],v[0]}; 
	p[6]=new Polygon3D(v6,Color.orange);
	Vertex3D[] v7={v[7],v[6],v[11],v[10],v[9],v[8]}; 
	p[7]=new Polygon3D(v7,Color.orange);
	//Graphics g=this.getGraphics();
	return new Shape3D(v,p);
}
/**
 * init a square DIMOND
 */

public Shape3D initMeroba(int i,int j)
{	
	Vertex3D[] v=new Vertex3D[8];
	v[0]=new Vertex3D(220+80*j,220+80*i,25);
	v[1]=new Vertex3D(250+80*j,220+80*i,25);
	v[2]=new Vertex3D(250+80*j,250+80*i,25);
	v[3]=new Vertex3D(220+80*j,250+80*i,25);
	
	v[4]=new Vertex3D(210+80*j,210+80*i,5);
	v[5]=new Vertex3D(210+80*j,270+80*i,5);
	v[6]=new Vertex3D(270+80*j,270+80*i,5);
	v[7]=new Vertex3D(270+80*j,210+80*i,5);
	
	Polygon3D[] p=new Polygon3D[6]; 
	Vertex3D[] v0={v[0],v[1],v[2],v[3]}; 
	p[0]=new Polygon3D(v0,Color.red);
    Vertex3D[] v1={v[1],v[7],v[6],v[2]}; 
	p[1]=new Polygon3D(v1,Color.red);
	Vertex3D[] v2={v[7],v[4],v[5],v[6]}; 
	p[2]=new Polygon3D(v2,Color.red);
	Vertex3D[] v3={v[4],v[0],v[3],v[5]}; 
	p[3]=new Polygon3D(v3,Color.red);
	Vertex3D[] v4={v[4],v[7],v[1],v[0]}; 
	p[4]=new Polygon3D(v4,Color.red);
	Vertex3D[] v5={v[5],v[3],v[2],v[6]}; 
	p[5]=new Polygon3D(v5,Color.red);
	
	return new Shape3D(v,p);
}
/**
 * init the 6TH dimond
 */

public Shape3D init6TH(int i,int j)
{
	Vertex3D[] th6=new Vertex3D[12];
	th6[0]=new Vertex3D(220+80*j,210+80*i,5);
	th6[1]=new Vertex3D(250+80*j,210+80*i,5);
	th6[2]=new Vertex3D(265+80*j,240+80*i,5);
	th6[3]=new Vertex3D(250+80*j,270+80*i,5);
	th6[4]=new Vertex3D(220+80*j,270+80*i,5);
	th6[5]=new Vertex3D(205+80*j,240+80*i,5);
	
	th6[6]=new Vertex3D(220+80*j,210+80*i,35);
	th6[7]=new Vertex3D(250+80*j,210+80*i,35);
	th6[8]=new Vertex3D(265+80*j,240+80*i,35);
	th6[9]=new Vertex3D(250+80*j,270+80*i,35);
	th6[10]=new Vertex3D(220+80*j,270+80*i,35);
	th6[11]=new Vertex3D(210+80*j,240+80*i,35);
	
	Polygon3D[] poly6=new Polygon3D[8]; 
	Vertex3D[] r0={th6[5],th6[4],th6[3],th6[2],th6[1],th6[0]};
	poly6[0]=new Polygon3D(r0,Color.green);
	Vertex3D[] r7={th6[0],th6[6],th6[11],th6[5]};
	poly6[1]=new Polygon3D(r7,Color.green);
	Vertex3D[] r1={th6[1],th6[7],th6[6],th6[0]};
	poly6[2]=new Polygon3D(r1,Color.green);
	Vertex3D[] r2={th6[1],th6[2],th6[8],th6[7]};
	poly6[3]=new Polygon3D(r2,Color.green);
	Vertex3D[] r3={th6[11],th6[10],th6[4],th6[5]};
	poly6[4]=new Polygon3D(r3,Color.green);
	Vertex3D[] r4={th6[3],th6[9],th6[8],th6[2]};
	poly6[5]=new Polygon3D(r4,Color.green);
	Vertex3D[] r5={th6[4],th6[10],th6[9],th6[3]};
	poly6[6]=new Polygon3D(r5,Color.green);
	Vertex3D[] r6={th6[6],th6[7],th6[8],th6[9],th6[10],th6[11]};
	poly6[7]=new Polygon3D(r6,Color.green);
	
	return new Shape3D(th6,poly6);
}
/**
 * init the triangular
 */

public Shape3D init1TH(int i, int j)
{
	Vertex3D[] rect=new Vertex3D[5];
	rect[4]=new Vertex3D(240+80*j,270+80*i,20);
	rect[0]=new Vertex3D(205+80*j,210+80*i,5);
	rect[1]=new Vertex3D(270+80*j,210+80*i,5);
	rect[2]=new Vertex3D(270+80*j,210+80*i,25);
	rect[3]=new Vertex3D(205+80*j,210+80*i,25);
	Polygon3D[] poly=new Polygon3D[5]; 
	Vertex3D[] rect0={rect[0],rect[1],rect[2],rect[3]};
	poly[0]=new Polygon3D(rect0,Color.yellow);
    Vertex3D[] rect1={rect[3],rect[2],rect[4]}; 
	poly[1]=new Polygon3D(rect1,Color.yellow);
	Vertex3D[] rect2={rect[2],rect[1],rect[4]}; 
	poly[2]=new Polygon3D(rect2,Color.yellow);
	Vertex3D[] rect3={rect[1],rect[0],rect[4]}; 
	poly[3]=new Polygon3D(rect3,Color.yellow);
	Vertex3D[] rect4={rect[0],rect[3],rect[4]}; 
	poly[4]=new Polygon3D(rect4,Color.yellow);
	
	return new Shape3D(rect,poly);
}
/**
 *  A function that is responsible for the delay of the shapes so that the transitions are visible
 */
public void delay()
{
	for(int i=0;i<99999999;i++);
	
}


}