package aI_Calc;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

//Little note of warning: GUI in java can be very redundant
//Therefore, a lot of this section will look the same

public class CalcView 
{

	protected Shell shlTrialCalculator;
	private Text text;
	//the file should always exist so this is okay. that way the file can be used in any button
	//alternative is localizing and using a boolean flag
	//one of the joys of HashMaps is that the same key will only be overwritten
	//this means the no duplicates will exist
	private static HashMap<String, String> currentHistory = FileManaging.pastHistory();

	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//I always use this line just to show in outputs that the file does exist.
		FileManaging.createFile();
		//runs until the calculator is closed due to being Event Driven	
		try {
			CalcView window = new CalcView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//closing out, recording the history for the next iteration
		FileManaging.storeHistory(currentHistory);
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlTrialCalculator.open();
		shlTrialCalculator.layout();
		while (!shlTrialCalculator.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlTrialCalculator = new Shell();
		shlTrialCalculator.setSize(540, 410);
		shlTrialCalculator.setText("Trial Calculator");
		
		text = new Text(shlTrialCalculator, SWT.BORDER);
		text.setEditable(false);
		text.setText("");
		text.setToolTipText("");
		text.setBounds(10, 10, 502, 50);
		
		
		//buttons are very repetitive and self explanatory
		//if they're clicked they display the given, for intance: 7 as shown
		Button btnNewButton = new Button(shlTrialCalculator, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				text.setText(text.getText()+"7");
			}
		});
		btnNewButton.setBounds(10, 129, 125, 50);
		btnNewButton.setText("7");
		
		Button btnNewButton_1 = new Button(shlTrialCalculator, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				text.setText(text.getText()+"8");
			}
		});
		btnNewButton_1.setText("8");
		btnNewButton_1.setBounds(141, 129, 125, 50);
		
		Button btnNewButton_2 = new Button(shlTrialCalculator, SWT.NONE);
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				text.setText(text.getText()+"9");
			}
		});
		btnNewButton_2.setText("9");
		btnNewButton_2.setBounds(272, 129, 125, 50);
		
		Button btnNewButton_3 = new Button(shlTrialCalculator, SWT.NONE);
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				text.setText(text.getText()+"4");
			}
		});
		btnNewButton_3.setText("4");
		btnNewButton_3.setBounds(10, 185, 125, 50);
		
		Button btnNewButton_4 = new Button(shlTrialCalculator, SWT.NONE);
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				text.setText(text.getText()+"5");
			}
		});
		btnNewButton_4.setText("5");
		btnNewButton_4.setBounds(141, 185, 125, 50);
		
		Button btnNewButton_5 = new Button(shlTrialCalculator, SWT.NONE);
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				text.setText(text.getText()+"6");
			}
		});
		btnNewButton_5.setText("6");
		btnNewButton_5.setBounds(272, 185, 125, 50);
		
		Button btnNewButton_6 = new Button(shlTrialCalculator, SWT.NONE);
		btnNewButton_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				text.setText(text.getText()+"1");
			}
		});
		btnNewButton_6.setText("1");
		btnNewButton_6.setBounds(10, 241, 125, 50);
		
		Button btnNewButton_7 = new Button(shlTrialCalculator, SWT.NONE);
		btnNewButton_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				text.setText(text.getText()+"2");
			}
		});
		btnNewButton_7.setText("2");
		btnNewButton_7.setBounds(141, 241, 125, 50);
		
		Button btnNewButton_8 = new Button(shlTrialCalculator, SWT.NONE);
		btnNewButton_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				text.setText(text.getText()+"3");
			}
		});
		btnNewButton_8.setText("3");
		btnNewButton_8.setBounds(272, 241, 125, 50);
		
		//Personally, for operators I write them as space operator space.
		//This makes it so I can parse using spaces and will be the main way of differentiation for numbers
		
		Button btnNewButton_9 = new Button(shlTrialCalculator, SWT.NONE);
		btnNewButton_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				text.setText(text.getText()+" / ");
			}
		});
		btnNewButton_9.setBounds(403, 66, 109, 57);
		btnNewButton_9.setText("/");
		
		Button btnNewButton_9_1 = new Button(shlTrialCalculator, SWT.NONE);
		btnNewButton_9_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				text.setText(text.getText()+" * ");
			}
		});
		btnNewButton_9_1.setText("*");
		btnNewButton_9_1.setBounds(403, 129, 109, 50);
		
		Button btnNewButton_9_2 = new Button(shlTrialCalculator, SWT.NONE);
		btnNewButton_9_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				text.setText(text.getText()+" - ");
			}
		});
		btnNewButton_9_2.setText("-");
		btnNewButton_9_2.setBounds(403, 185, 109, 50);
		
		Button btnNewButton_9_3 = new Button(shlTrialCalculator, SWT.NONE);
		btnNewButton_9_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				text.setText(text.getText()+" + ");
			}
		});
		btnNewButton_9_3.setText("+");
		btnNewButton_9_3.setBounds(403, 241, 109, 50);
		
		
		/// + or - could be handled using math and multiplying by -1
		// I wasn't so wise when I wrote this so it uses absolute positions and strings
		
		Button btnNewButton_6_1 = new Button(shlTrialCalculator, SWT.NONE);
		btnNewButton_6_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//turns current number negative
				if(!text.getText().contains(" "))
				{
					if(text.getText().contains("-"))
						text.setText(text.getText().substring(1));
					else
						text.setText("-" + text.getText());
				}
				else
				{
					//case 2 is where you are modifying an instance after the first
					if (text.getText().substring(text.getText().lastIndexOf(" ")+1).contains("-"))
						text.setText((text.getText().substring(0, text.getText().lastIndexOf(" "))) + text.getText().substring(text.getText().lastIndexOf(" ")).replace("-", ""));
					else
						text.setText(text.getText().substring(0, text.getText().lastIndexOf(" ")+1) + "-"+text.getText().substring(text.getText().lastIndexOf(" ")+1));
						
				}
			}
		});
		btnNewButton_6_1.setText("+/-");
		btnNewButton_6_1.setBounds(10, 297, 125, 50);
		
		//For zeroes I don't want leading on either number so I fixed that
		Button btnNewButton_6_2 = new Button(shlTrialCalculator, SWT.NONE);
		btnNewButton_6_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//if it was empty it doesn't add a 0.
				if(text.getText().isBlank())
					{}
				//have to make sure a space exists. and then if the string beyond it isn't blank
				else if(text.getText().indexOf(" ") > 0 && text.getText().substring(text.getText().lastIndexOf(" ")).isBlank())
					{}
				else
					text.setText(text.getText()+"0");
			}
		});
		btnNewButton_6_2.setText("0");
		btnNewButton_6_2.setBounds(141, 297, 125, 50);
		
		//decimals follow the same logic as negatives, except they can't exist with any more than one
		Button btnNewButton_6_3 = new Button(shlTrialCalculator, SWT.NONE);
		btnNewButton_6_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				//can't allow 2 decmial points in same number
				if(text.getText().indexOf(" ") < 0 && text.getText().indexOf(".") > -1)
					{}
				else if(text.getText().indexOf(" ") > 0 && text.getText().substring(text.getText().lastIndexOf(" ")).contains("."))
					{}
				else
					text.setText(text.getText()+".");
			}
		});
		btnNewButton_6_3.setText(".");
		btnNewButton_6_3.setBounds(272, 297, 125, 50);
		
		//clearing buttons are direct, sets to blank
		Button btnNewButton_10 = new Button(shlTrialCalculator, SWT.NONE);
		btnNewButton_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				text.setText("");
			}
		});
		btnNewButton_10.setBounds(10, 66, 125, 57);
		btnNewButton_10.setText("Clear Everything");
		
		//deletes by section, including the 2 spaces and operator when needed
		Button btnNewButton_11 = new Button(shlTrialCalculator, SWT.NONE);
		btnNewButton_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) 
			{
				if(text.getText().contains(" "))
				{
					//pretty sure it's exclusive so add one
					if(text.getText().substring(text.getText().length()-1).equals(" "))
					{
						//using composite numbers, could be better later I guess
						//3 spaces so it gets rid of both spaces and the character
						text.setText(text.getText().substring(0, text.getText().length()-3));
					}
					else
						text.setText(text.getText().substring(0, text.getText().lastIndexOf(" ")+1));
				}
				else
					text.setText("");
			}
		});
		btnNewButton_11.setBounds(141, 66, 125, 57);
		btnNewButton_11.setText("Clear Current");
		
		//deletes last thing entered, so length-1
		Button btnNewButton_12 = new Button(shlTrialCalculator, SWT.NONE);
		btnNewButton_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//removing last character typed
				text.setText(text.getText().substring(0, text.getText().length()-1));
			}
		});
		btnNewButton_12.setBounds(272, 66, 125, 57);
		btnNewButton_12.setText("Remove Last");
		
		//equals button is what drives the whole program
		//things will only be simplified when = is hit that way I can parse everything at once
		Button btnNewButton_13 = new Button(shlTrialCalculator, SWT.NONE);
		btnNewButton_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) 
			{
				//things to remember
				//PEMDAS, return string and then do next calculation in the order it was parsed.
				//probably trim before parsing. should return a singular answer which can then
				//be calculated yet again
				//maybe two arrays and compare them. order will be practical
				String labelString = text.getText().trim();
				//System.out.println("LS: " + labelString);
				
				String[] unparsed = labelString.split(" ");
				for(String s: unparsed)
				 System.out.println(s);
				
				ArrayList<Double> strNums = new ArrayList<Double>();
				ArrayList<String> strOperators = new ArrayList<String>();
				
				
				//parse based on spaces
				for(int i = 0; i < unparsed.length; i++)
				{
					switch(unparsed[i])
					{
						case "+":
						{
							strOperators.add("+");
							break;
						}
						case "-":
						{
							strOperators.add("-");
							break;
						}
						case "*":
						{
							strOperators.add("*");
							break;
						}
						case "/":
						{
							strOperators.add("/");
							break;
						}
						default:
						{
							strNums.add(Double.parseDouble(unparsed[i]));
							break;
						}
					}
				}
								
				//this is the AI. Simple Reflex that either records and spits out,
				//or simply spits out from memory.
				
				//if found not found, store, then display. This allows it to learn.
				if(currentHistory.get(labelString) == null)
				//anything in here will be added after the current history
				{
					System.out.println("Calculated Value");
					double answer = MathHelper.calc(strOperators, strNums);
					currentHistory.put(labelString, MathHelper.formatDecimals(answer));					
				}
					
				//will always trigger. if found it skips calculating.
				text.setText(currentHistory.get(labelString));
				System.out.println("After Output, Current History: " + currentHistory);
				
				//when first placed in HashMap it will be sorted with alphabetical,
				//but over multiple trials it will not be because it replaces exact key without changing
				
			}
		});
		btnNewButton_13.setBounds(403, 297, 109, 50);
		btnNewButton_13.setText("=");

		//as noted, program runs until calculator is closed.
		//it carries history so it can act as a table-driven reflex agent
	}
}
