package com.mycompany.tictactoe;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.graphics.*;
import android.view.View;
import android.view.View.*;

public class MainActivity extends Activity 
{
	
	//global variables
	boolean flag = true;
	boolean win = false;
	int count;
	int clicks = 0;
	int[][] compatible = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
	boolean[] pressed = new boolean [9];
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		//buttons
		for (int i = 0; i < 9; i++) {
			pressed[i] = false;
		}
		
		final Button[] buttons = new Button [9];
		buttons[0] = findViewById(R.id.topLeft);
		buttons[1] = findViewById(R.id.topMid);
		buttons[2] = findViewById(R.id.topRight);
		buttons[3] = findViewById(R.id.midLeft);
		buttons[4] = findViewById(R.id.midMid);
		buttons[5] = findViewById(R.id.midRight);
		buttons[6] = findViewById(R.id.bottomLeft);
		buttons[7] = findViewById(R.id.bottomMid);
		buttons[8] = findViewById(R.id.bottomRight);
		
		for (Button b: buttons) {
			b.setBackgroundColor(Color.rgb(245, 220, 225));
			b.setTextSize(50);
			
		    b.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					
					clicks++;
					
					for (int i = 0; i < 9; i++) {
						//get pressed buttom
						if (v == buttons[i]) {count = i;}
					}

					if (!pressed[count] && !win) {
						pressed[count] = true;
						if (flag) {
							flag = false;
							buttons[count].setText("✓");
						}
						else {
							flag = true;
							buttons[count].setText("×");
						}

					}
					
					if (clicks >= 5) {
						//check whether someone wins
						for (int[] i: compatible) {
							if (buttons[i[0]].getText() == buttons[i[1]].getText() &&
							buttons[i[0]].getText() == buttons[i[2]].getText() && 
							!buttons[i[0]].getText().equals("")) {
								win = true;
								
								for (int j = 0; j < 3; j++) {
								buttons[i[j]].setBackgroundColor(Color.rgb(255, 0, 0));
								buttons[i[j]].setTextColor(Color.rgb(0, 255, 255));
								}
							}
							
						}
						
					}
					
				}
			});
			
		}
		Button resetButton = (Button) findViewById(R.id.resetButton);
		resetButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
			    //reset
			    for (Button b: buttons) {
				    b.setText("");
				    b.setBackgroundColor(Color.rgb(245, 220, 225));
					b.setTextColor(Color.BLACK);
				}
					
				flag = true;
				win = false;
				clicks = 0;
				
				for (int i = 0; i < 9; i++) {
					pressed[i] = false;
				}
				
			}
		});
		
    }
	
}
