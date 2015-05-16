import java.awt.Dimension;  
import java.awt.Font;  
import java.awt.Graphics;  
import java.awt.GridLayout;  
import java.awt.Image;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
  
import javax.swing.ImageIcon;  
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JPanel;  
@SuppressWarnings("serial")  
public class Chess extends JFrame {  
    public static void main(String args[]){  
        Chess ch=new Chess();  
        ch.showframe();  
    }  
    //显示游戏界面  
    public void showframe(){  
        //--------------------游戏界面----------------------  
        JPanel gamejp=new JPanel();  
        //--------------------组件界面----------------------  
        JPanel buttonjp=new JPanel();  
        //按钮图标  
        ImageIcon ali=new ImageIcon("image/阿狸.jpg");  
        ImageIcon taozi=new ImageIcon("image/桃子.jpg");  
        ImageIcon ali1=new ImageIcon("image/阿狸1.jpg");  
        ImageIcon taozi1=new ImageIcon("image/桃子1.jpg");  
        JButton alibu=new JButton(ali);  
        JButton taozibu=new JButton(taozi);  
        alibu.setRolloverIcon(ali1);  
        taozibu.setRolloverIcon(taozi1);  
        alibu.setPressedIcon(ali1);  
        taozibu.setPressedIcon(taozi1);  
        Dimension di=new Dimension(100,100);  
        alibu.setPreferredSize(di);  
        taozibu.setPreferredSize(di);  
        //-----------------------当前下棋的人---------------------  
        final JLabel jilu=new JLabel("  阿狸下  ");  
        //设置字体  
        Font jilufont=new Font("黑体",Font.BOLD,30);  
        jilu.setFont(jilufont);  
        //用来记录阿狸与桃子的数目  
        final JLabel alila=new JLabel("2");  
        final JLabel taozila=new JLabel("2");  
        //设置Label的字体和大小  
        Font font=new Font("宋体",Font.BOLD,42);  
        alila.setFont(font);  
        taozila.setFont(font);  
        //-----------------重新开局的方法------------------  
        ImageIcon img=new ImageIcon("image/restart.jpg");  
        JButton bu=new JButton(img);  
        bu.setPreferredSize(new Dimension(100,40));  
        buttonjp.add(jilu);  
        buttonjp.add(alibu);  
        buttonjp.add(alila);  
        buttonjp.add(taozibu);  
        buttonjp.add(taozila);  
        buttonjp.add(bu);  
        this.setLayout(new GridLayout(1,2,600,0));  
        this.add(gamejp);  
        this.add(buttonjp);  
        this.setTitle("阿狸&桃子");  
        this.setSize(1000,650);  
        this.setResizable(false);  
        this.setLocationRelativeTo(null);  
        this.setDefaultCloseOperation(3);  
        //显示窗口  
        this.setVisible(true);  
        //获取画布权限  
        Graphics g=this.getGraphics();  
        chess[3][3]=1;  
        chess[4][4]=1;  
        chess[3][4]=-1;  
        chess[4][3]=-1;  
        ChessListener lis=new ChessListener(g,this,jilu,alila,taozila);  
        this.addMouseListener(lis);  
        //按钮监听器----------------重新开始  
        ActionListener bulis=new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                clear();  
                //重绘的方法  
                repaint();  
                //绘制初始的四个图片  
                chess[3][3]=1;  
                chess[4][4]=1;  
                chess[3][4]=-1;  
                chess[4][3]=-1;  
                //将状态改为初始状态  
                ChessListener.stata=1;  
                jilu.setText("  阿狸下  ");  
                alila.setText("2");  
                taozila.setText("2");  
            }  
        };  
        //如果点击阿狸则阿狸下  
        ActionListener alilis=new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                ChessListener.stata=1;  
                jilu.setText("  阿狸下  ");  
            }  
        };  
        //如果点击桃子则桃子下  
        ActionListener taozilis=new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                ChessListener.stata=-1;  
                jilu.setText("  桃子下  ");  
            }  
        };  
        alibu.addActionListener(alilis);  
        taozibu.addActionListener(taozilis);  
        bu.addActionListener(bulis);  
    }  
    /** 
     * 清空棋盘的方法 
     */  
    public void clear(){  
        for(int i=0;i<chess.length;i++){  
            for(int j=0;j<chess[i].length;j++){  
                chess[i][j]=0;  
            }  
        }  
    }  
    /** 
     * 重写父类的paint方法 
     */  
    public void paint(Graphics g){  
        super.paint(g);  
        Image back=new ImageIcon("image/棋盘.jpg").getImage();  
        g.drawImage(back, 10,30,800,600, null);  
        //重绘棋盘  
        //划横线  
//      for(int i=0;i<rows;i++){  
//          g.setColor(Color.BLUE);  
//          g.drawLine(x,y+i*size,x+size*(rows-1,y+i*size);  
//      }  
//      //划竖线  
//      for(int j=0;j<cols;j++){  
//          g.setColor(Color.BLUE);  
//          g.drawLine(x+j*size,y,x+j*size,y+size*(cols-1));  
//      }  
        //绘制棋子  
        for(int i=0;i<rows-1;i++){  
            for(int j=0;j<cols-1;j++){  
                int X=x+size/2+size*i;//棋子的横坐标  
                int Y=y+size/2+size*j;//棋子的纵坐标  
                if(chess[i][j]==1){  
                    //画黑棋  
                    Image b=new ImageIcon("image/ali.jpg").getImage();  
                    g.drawImage(b, X-Chess_size/2, Y-Chess_size/2, Chess_size, Chess_size, null);  
                    //g.fillOval(X-size/2,Y-size/2,Chess_size,Chess_size);  
                }  
                else if(chess[i][j]==-1){  
                    //画白棋  
                    Image w=new ImageIcon("image/taozi.jpg").getImage();  
                    g.drawImage(w, X-Chess_size/2, Y-Chess_size/2, Chess_size, Chess_size, null);  
                    //g.fillOval(X-size/2,Y-size/2,Chess_size,Chess_size);  
                }  
            }  
        }  
    }  
    public final static int x=79;   //棋盘初始点横坐标  
    public final static int y=83;   //棋盘初始点纵坐标  
    public final static int rows=9; //棋盘行数  
    public final static int cols=9; //棋盘列数  
    public final static int size=61;//棋盘格子大小  
    public final static int Chess_size=56;//棋子大小  
    public final static int chess[][]=new int[rows-1][cols-1];//定义一个8*8的数组，用来保存棋子  
}  
