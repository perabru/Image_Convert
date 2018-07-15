
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

public class CompareFeedback {

	public void compareAns(ArrayList<Integer> finalGrade) {

		// Initializing the array - NOT NEEDED TO INITALIZE
		// finalGrade = new ArrayList<Integer>();
		// System.out.println("SIZE OF " + finalGrade.size());

		int grade = 0;
		int studentFinalGrade = 0;

		int iteratorX = 0;
		int iteratorY = 1;
		int iteratorX1 = 20;
		int iteratorY1 = 21;

		int testX = finalGrade.get(iteratorX);
		int testX1 = finalGrade.get(iteratorX1);
		int testY = finalGrade.get(iteratorY);
		int testY1 = finalGrade.get(iteratorY1);

		for (int i = 0; i < 10; i++) {

			if (testX == testX1 && testY == testY1) {
			//if(testX ==0 || testX < 25  && testX1 == 0 || testX1 < 25 && testY == 0 || testY < 25 && testY1 ==0 || testY1 <25) {

				grade++;
			}

			testX += 2;
			testX1 += 2;
			testY += 2;
			testY1 += 2;
			System.out.println(testX + " "+testX1 + " "+testY+ " "+testY1);

		}
	

		JOptionPane.showMessageDialog(null, "grade: " + grade);

	}

}
