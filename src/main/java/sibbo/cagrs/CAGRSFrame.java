package sibbo.cagrs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.omg.CORBA.portable.InputStream;

public class CAGRSFrame extends JFrame implements ActionListener {
	private JButton random;
	private JTextArea result;
	private Random r;

	private String[] black, white;

	public CAGRSFrame() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(
				"white.txt")));
		white = in.readLine().split("<>");
		in = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("black.txt")));
		black = in.readLine().split("<>");

		actionPerformed(null);

		random = new JButton("Random!");
		random.addActionListener(this);

		result = new JTextArea("", 10, 30);
		result.setFont(new Font("arial", Font.PLAIN, 20));
		result.setWrapStyleWord(true);
		result.setEditable(false);

		getContentPane().setLayout(new BorderLayout());

		getContentPane().add(BorderLayout.NORTH, result);
		getContentPane().add(BorderLayout.SOUTH, random);

		// setMinimumSize(new Dimension(640, 480));
		setTitle("Cards against Humanity");
		pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String question = black[r.nextInt(black.length)];
		String[] parts = question.split("__________");

		StringBuilder answer = new StringBuilder();
		answer.append(parts[0]);
		
		for (int i = 1; i < parts.length; i++) {
			answer.append(white[r.nextInt(white.length)]);
			answer.append(parts[i]);
		}
		
		result.setText(answer.toString());
	}

	public static void main(String[] args) throws IOException {
		new CAGRSFrame();
	}
}