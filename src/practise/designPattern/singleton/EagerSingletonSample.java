/**
 * 
 */
package practise.designPattern.singleton;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月23日 下午8:32:29
* Description:
*/

public class EagerSingletonSample {

	public static void main(String[] args) {
		JFrame jFrame = new JFrame("qinqin");
		jFrame.setLayout(new GridLayout(1,2));
		Container panel = jFrame.getContentPane();
		Bajie qin = Bajie.getInstance();
		panel.add(qin);
		Bajie qin2 = Bajie.getInstance();
		panel.add(qin2);
		if(qin == qin2) {
			System.out.println("same");
		} else {
			System.out.println("different");
		}
		jFrame.pack();
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class Bajie extends JPanel{
	private static Bajie instance = new Bajie();
	
	private Bajie() {
		
		JLabel l1 = new JLabel(new ImageIcon("E:\\Dev_Software\\test\\qin"));
		System.out.println(l1);
		this.add(l1);
	}
	
	public static Bajie getInstance() {
		return instance;
	}
	
}

