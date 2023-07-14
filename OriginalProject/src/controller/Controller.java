package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import javax.swing.MenuElement;

import module.AmericanQuestions;
import module.Answers;
import module.CommandCase10;
import module.CommandCase1;
import module.CommandCase9;
import module.OpenQuestions;
import module.OperatingSystem;
import module.OperatingSystemModule;
import module.Questions;
import module.Test;
import view.Case1;
import view.Case11;
import view.Case2;
import view.Case3;
import view.Case4;
import view.Case5;
import view.Case6;
import view.Case7;
import view.Case8;
import view.Menu;

public class Controller {
	private Menu menu;
	private OperatingSystemModule module;
	private Case1 case1;
	private Case2 case2;
	private Case3 case3;
	private Case4 case4;
	private Case5 case5;
	private Case6 case6;
	private Case7 case7;
	private Case8 case8;
	private Case11 case11;
	private AmericanQuestions question;
	private Questions question2;
	private Test manualTest ;
	private Iterator<Test> it;
	
	
	public Controller(Menu menu, OperatingSystemModule module, Case1 case1, Case2 case2, Case3 case3, Case4 case4,
			Case5 case5, Case6 case6, Case7 case7, Case8 case8,Case11 case11) {
		this.menu = menu;
		this.module = module;
		this.case1 = case1;
		this.case2 = case2;
		this.case3 = case3;
		this.case4 = case4;
		this.case5 = case5;
		this.case6 = case6;
		this.case7 = case7;
		this.case8 = case8;
		this.case11 = case11;
		

		try {
			module.loadPoolOfQuestionsAndTestsFromFile();

			menu.setExitText3Visibility(true);
			menu.setStackPaneVisible();
			menu.showStage();
		} catch (ClassNotFoundException | IOException e1) {
			menu.setExitText4Visibility(true);
			menu.setStackPaneVisible();
			menu.showStage();

			e1.printStackTrace();
		}
		/*module.constructOpenQuestion("how many notes does the major scale has?", module.constructAnswer("7", true));
		module.constructOpenQuestion("what scale is considered the scale of shabat by the jewish religion?", module.constructAnswer("hijaz", true));
		module.constructOpenQuestion("what scale is considered the scale of shabat by the jewish religion?", module.constructAnswer("hijaz", true));
		AmericanQuestions exampleQuestion = module.contructAmericanQuestion("what kind of scales are used in japanese traditional music?");
		module.contructAnswerForAmerican(exampleQuestion, "hexatonic scales", false);
		module.contructAnswerForAmerican(exampleQuestion, "heptatonic scales", false);
		module.contructAnswerForAmerican(exampleQuestion, "chromatic scales", false);
		module.contructAnswerForAmerican(exampleQuestion, "pentatonic scales", true);
		AmericanQuestions scales = module.contructAmericanQuestion("what is the origin of blues?");
	    module.contructAnswerForAmerican(scales, "african music", false);
		module.contructAnswerForAmerican(scales,
				"african rythms and scales combined with classical instruments", true);
		module.contructAnswerForAmerican(scales, "make america great again", false);
		module.contructAnswerForAmerican(scales, "country music combined with classical composition", false);
		module.contructAnswerForAmerican(scales, "south american folkore", false);
		module.contructAnswerForAmerican(scales, "gypsy music", false);
		*/

		case1.addTextToWindow(module.toString(module.getArrayOfquestions()));
		case3.addTextToWindow(module.toString(module.getArrayOfquestions()));
		case4.addTextToWindow(module.toString(module.getArrayOfquestions()));
		case5.addTextToWindow(module.toString(module.getArrayOfquestions()));
		case6.addTextToWindow(module.toString(module.getArrayOfquestions()));
		case3.setComboBox(module.getNumOfQuestionsInArray());
		case4.setComboBoxQuestion(module.getNumOfQuestionsInArray());
		case5.setComboBoxQuestion(module.getNumOfQuestionsInArray());
		case6.setNumberOfQuestionsInDataBase(module.getNumOfQuestionsInArray());
		case7.setComboBox(module.getNumOfQuestionsInArray());
		case8.setTextListToWindow(module.showAllTests());
		case8.setComboBox(module.getNumOfTests());
		menu.addMyEventHandlerForCase1(e -> {
			CommandCase1 cmd = new CommandCase1(case1,module);
			cmd.execute();
			//case1.addTextToWindow(module.toString(module.getArrayOfquestions()));
			//case1.showWindow();
		});
		

		menu.addMyEventHandlerForCase2(e -> {
			case2.showWindow();

		});

		menu.addMyEventHandlerForCase3(e -> {
			case3.addTextToWindow(module.toString(module.getArrayOfquestions()));
			case3.showWindow();

		});
		menu.addMyEventHandlerForCase4(e -> {
			case4.addTextToWindow(module.toString(module.getArrayOfquestions()));
			case4.showWindow();

		});
		menu.addMyEventHandlerForCase5(e -> {
			case5.addTextToWindow(module.toString(module.getArrayOfquestions()));
			case5.showWindow();

		});
		menu.addMyEventHandlerForCase6(e -> {
			case6.addTextToWindow(module.toString(module.getArrayOfquestions()));
			case6.showWindow();

		});
		menu.addMyEventHandlerForCase7(e -> {
			case7.setComboBox(module.getNumOfQuestionsInArray());
			case7.showWindow();

		});
		menu.addMyEventHandlerForCase8(e -> {
			case8.setTextListToWindow(module.showAllTests());
			case8.setComboBox(module.getNumOfTests());
			case8.showWindow();

		});

		case2.addMyEventHandlerOk(e -> {
			for (int j = 0; j < case2.getSelectedIndex(); j++) {
				if (case2.getAnswerTextFieldArray().get(j).getText().isEmpty()) {
					case2.setNotEnoughAnswersErrorVisible(true);
				}
			}
			if (case2.getQuestionTextField().getText().isEmpty() || case2.getAnswerTextFieldArrayEmpty()) {
				if (case2.getQuestionTextField().getText().isEmpty()) {
					case2.setQuestionBlankErrorVisible(true);
				} else {
					case2.setAnswerBlankErrorvisible(true);
				}
			} else {
				if (case2.getRadioButton1().isSelected()) {
					if (module.checkIfQuestionExistViaQuestion(
							module.constructOpenQuestion(case2.getQuestionTextField().getText(),
									module.constructAnswer(case2.getAnswerTextFieldArray().get(0).getText(), true)))) {
						case2.getQuestionTextError().setVisible(true);

					} else {
						case2.changeOkVisiblity(false, true);
						case2.changeborderPaneScene();
						case1.addTextToWindow(module.toString(module.getArrayOfquestions()));
						case3.addTextToWindow(module.toString(module.getArrayOfquestions()));
						case4.addTextToWindow(module.toString(module.getArrayOfquestions()));
						case5.addTextToWindow(module.toString(module.getArrayOfquestions()));
						case6.addTextToWindow(module.toString(module.getArrayOfquestions()));
						case3.addToComboBox();
						case4.addToComboBoxQuestion();
						case5.addToComboBoxQuestion();
						case6.addToComboBox();
						case7.setComboBox(module.getNumOfQuestionsInArray());

					}
				} else if (case2.getRadioButton2().isSelected()) {
					boolean sameAnswerTwice = false;
					int fAnswer = 0;
					int sAnswer = 0;
					if (case2.getQuestionTextField().getText() == null) {
						case2.setQuestionBlankErrorVisible(true);
					} else {
						for (int i = 0; i < (case2.getSelectedIndex() - 1); i++) {
							for (int j = (i + 1); j < case2.getSelectedIndex(); j++)
								if (case2.getAnswerTextFieldArray().get(i).getText()
										.equalsIgnoreCase(case2.getAnswerTextFieldArray().get(j).getText())) {
									fAnswer = i;
									sAnswer = j;
									sameAnswerTwice = true;

								}
						}
						if (sameAnswerTwice) {
							case2.displayAnswerError(fAnswer, sAnswer);
						} else if (case2.getNumberOfAnswers().getValue() == null) {
							case2.setNotEnoughAnswersErrorVisible(true);
						} else {
							question = module.contructAmericanQuestion(case2.getQuestionTextField().getText());
							if (module.checkIfQuestionExistViaQuestion(question)) {
								case2.getQuestionTextError().setVisible(true);
							} else {
								for (int h = 0; h < case2.getSelectedIndex(); h++) {
									module.contructAnswerForAmerican(question,
											case2.getAnswerTextFieldArray().get(h).getText(),
											case2.getcBoxArray().get(h).isSelected());
								}
								case2.changeOkVisiblity(false, true);
								case2.changeborderPaneScene();
								case1.addTextToWindow(module.toString(module.getArrayOfquestions()));
								case3.addTextToWindow(module.toString(module.getArrayOfquestions()));
								case4.addTextToWindow(module.toString(module.getArrayOfquestions()));
								case5.addTextToWindow(module.toString(module.getArrayOfquestions()));
								case6.addTextToWindow(module.toString(module.getArrayOfquestions()));
								case3.addToComboBox();
								case4.addToComboBoxQuestion();
								case5.addToComboBoxQuestion();
								case6.addToComboBox();
								case7.setComboBox(module.getNumOfQuestionsInArray());
							}
						}
					}
				} else {
					case2.setRadioButtonErrorVisible(true);
				}

			}
		});
		case2.addMyEventHandlerOk2(e -> {
			case2.resetStage();
			case2.close();

		});
		case3.addMyEventHandlerOk(e -> {
			if (case3.getQuestionNum().getValue() == null) {
				case3.setNoQuestionFoundErrorVisible(true);
			} else {

				if (case3.getNewWording().getText().isEmpty()) {
					case3.setNewWordingAlreadyExistsErrorVisible(true);
				} else {
					module.changeBodyOfQuestion(case3.getQuestionNum().getValue() - 1, case3.getNewWording().getText());
					if (module.checkIfQuestionExistViaText(module.getQuestion(case3.getQuestionNum().getValue() - 1))) {
						case3.setNewWordingAlreadyExistsErrorVisible(true);
					} else {
						case3.changeborderPaneScene();
						case1.addTextToWindow(module.toString(module.getArrayOfquestions()));
						case3.addTextToWindow(module.toString(module.getArrayOfquestions()));
						case4.addTextToWindow(module.toString(module.getArrayOfquestions()));
						case5.addTextToWindow(module.toString(module.getArrayOfquestions()));
						case6.addTextToWindow(module.toString(module.getArrayOfquestions()));

					}
				}

			}
		});
		case3.addMyEventHandlerOk2(e -> {
			case3.resetStage();
			case3.close();

		});
		case4.addMyEventHandlerForQuestionComboBox(e -> {
			if (case4.getQuestionNum().getValue() != null
					&& module.getQuestion(case4.getQuestionNum().getValue() - 1) instanceof AmericanQuestions) {
				case4.getAnswerNum().getItems().clear();
				case4.setComboBoxAnswer(((AmericanQuestions) module.getQuestion(case4.getQuestionNum().getValue() - 1))
						.getNumberOfAnswers());
				case4.setAnswerNumberVisible(true);
				case4.setAnswerNumVisible(true);
			} else {
				case4.setAnswerNumberVisible(false);
				case4.setAnswerNumVisible(false);
			}

		});
		case4.addMyEventHandlerForOk1(e -> {
			if (case4.getQuestionNum().getValue() == null) {
				case4.setQuestionNumberErrorVisible(true);
			} else if (case4.getAnswerNum().getValue() == null
					&& module.getQuestion(case4.getQuestionNum().getValue() - 1) instanceof AmericanQuestions) {
				case4.setAnswerNumberErrorVisible(true);
			} else if (case4.getAnswerTextField().getText().isEmpty()) {
				case4.setAnswerTextEmptyErrorVisible(true);
			} else if (module.getQuestion(case4.getQuestionNum().getValue() - 1) instanceof AmericanQuestions
					&& module.checkIfAnswerTextExist(
							((AmericanQuestions) module.getQuestion(case4.getQuestionNum().getValue() - 1)),
							case4.getAnswerTextField().getText())) {
				case4.setAnswerWithSameTextErrorVisible(true);

			} else {
				if (case4.getAnswerNum().getValue() == null) {
					module.changeAnswer(module.getQuestion(case4.getQuestionNum().getValue() - 1), 1,
							case4.getAnswerTextField().getText());
					case4.changeborderPaneScene();
					case1.addTextToWindow(module.toString(module.getArrayOfquestions()));
					case3.addTextToWindow(module.toString(module.getArrayOfquestions()));
					case4.addTextToWindow(module.toString(module.getArrayOfquestions()));
					case5.addTextToWindow(module.toString(module.getArrayOfquestions()));
					case6.addTextToWindow(module.toString(module.getArrayOfquestions()));

				} else {
					module.changeAnswer(module.getQuestion(case4.getQuestionNum().getValue() - 1),
							case4.getAnswerNum().getValue() - 1, case4.getAnswerTextField().getText());
					case4.changeborderPaneScene();
					case1.addTextToWindow(module.toString(module.getArrayOfquestions()));
					case3.addTextToWindow(module.toString(module.getArrayOfquestions()));
					case4.addTextToWindow(module.toString(module.getArrayOfquestions()));
					case5.addTextToWindow(module.toString(module.getArrayOfquestions()));
					case6.addTextToWindow(module.toString(module.getArrayOfquestions()));

				}
			}

		});
		case4.addMyEventHandlerOk2(e -> {
			case4.resetStage();
			case4.close();

		});
		case5.addMyEventHandlerForQuestionComboBox(e -> {
			case5.getAnswerNum().getItems().clear();
			if (case5.getQuestionNum().getValue() != null) {
				if (module.getQuestion(case5.getQuestionNum().getValue() - 1) instanceof OpenQuestions) {
					case5.setOpenQuestionErrorVisible(true);
				} else {
					case5.setComboBoxAnswer(
							((AmericanQuestions) module.getQuestion(case5.getQuestionNum().getValue() - 1))
									.getNumberOfAnswers());

				}

			}
		});

		case5.addMyEventHandlerForOk1(e -> {
			if (case5.getQuestionNum().getValue() == null) {
				case5.setQuestionNumberErrorVisible(true);
			} else if (module.getQuestion(case5.getQuestionNum().getValue() - 1) instanceof OpenQuestions) {
				case5.setOpenQuestionErrorVisible(true);
			} else if (case5.getAnswerNum().getValue() == null) {
				case5.setAnswerNumberErrorVisible(true);
			} else if (!module.deleteAnswer(
					(AmericanQuestions) module.getQuestion(case5.getQuestionNum().getValue() - 1),
					case5.getAnswerNum().getValue())) {
				case5.setAtLeastFourErrorVisible(true);
			} else {
				case5.changeborderPaneScene();
				case1.addTextToWindow(module.toString(module.getArrayOfquestions()));
				case3.addTextToWindow(module.toString(module.getArrayOfquestions()));
				case4.addTextToWindow(module.toString(module.getArrayOfquestions()));
				case5.addTextToWindow(module.toString(module.getArrayOfquestions()));
				case6.addTextToWindow(module.toString(module.getArrayOfquestions()));

			}
		});
		case5.addMyEventHandlerOk2(e -> {
			case5.resetStage();
			case5.close();

		});
		case6.addMyEvenHandlerForQuestionNumComboBox(e -> {
			case6.clearBottomCenterPane();
			case6.clearNumOfAnswers();
			if (case6.getNumOfQuestion() != 0
					&& module.getQuestion(case6.getNumOfQuestion() - 1) instanceof AmericanQuestions) {
				case6.setChooseNumOfAnswers(true);
				case6.setNumOfAnswers(true);
				case6.setValuesfornumOfAnswersForMainBox(
						((AmericanQuestions) module.getQuestion(case6.getNumOfQuestion() - 1)).getNumberOfAnswers());

			} else {
				case6.setChooseNumOfAnswers(false);
				case6.setNumOfAnswers(false);
			}
		});
		case6.addMyEvenHandlerForOk1(e -> {

			if (case6.getNumOfQuestionsInTest() != 0 && case6.getCurrentNumOfQuestions() == 1) {
				manualTest = module.createTest(case6.getNumOfQuestionsInTest());
				case6.setTestCreatedAlready(true);
			}
			if (case6.isNumOfQuestionsInTestVisible() && case6.getNumOfQuestionsInTest() == 0) {
				case6.setNumberOfQuestionsErrorVisible();
			} else if (case6.isNumOfQuestionVisible() && case6.getNumOfQuestion() == 0) {
				case6.setQuestionErrorVisible();
			} else if (module.getQuestion((case6.getNumOfQuestion() - 1)) instanceof AmericanQuestions) {
				if (case6.getNumOfAnswers() == 0) {
					case6.setNumberOfAnswersErrorVisible(true);
				} else if (case6.checkThatAllOptionsAreFilled()) {

					AmericanQuestions newQuestion = module.getOs().contructAmericanQuestion(
							(AmericanQuestions) module.getQuestion((case6.getNumOfQuestion() - 1)),
							case6.getNumOfAnswers() + 2);
					for (int i = 0; i < case6.getNumOfAnswers(); i++) {
						module.contructAnswerForAmerican(newQuestion,
								module.getAnswer(
										((AmericanQuestions) module.getQuestion((case6.getNumOfQuestion() - 1))),
										case6.getValueOfAnswerBox(i)).getBodyOfAnswer(),
								module.getAnswer(
										((AmericanQuestions) module.getQuestion((case6.getNumOfQuestion() - 1))),
										case6.getValueOfAnswerBox(i)).isCorrect());

					}
					module.verifyAnswers(newQuestion);
					module.addQuestionToTest(newQuestion, manualTest);
					case6.removeItemFromQuestionNum(case6.getNumOfQuestion());
					case6.addToCurrentNumOfQuestions();
					if (case6.getCurrentNumOfQuestions() <= case6.getNumOfQuestionsInTest()) {
						case6.setMessagePane(true);
					} else {
						try {
							module.saveTestToFile(manualTest);
						} catch (FileNotFoundException e1) {
							case6.setSaveTestErrorVisible();
							e1.printStackTrace();
						}
						case6.setCreatedTestPane(module.printTest(manualTest));
						module.testSortViaLengthOfAnswers(manualTest);
						case8.setTextListToWindow(module.showAllTests());
						case8.setComboBox(module.getNumOfTests());
					}
				}
			} else if (module.getQuestion((case6.getNumOfQuestion() - 1)) instanceof OpenQuestions) {
				module.addQuestionToTest(module.getQuestion((case6.getNumOfQuestion() - 1)), manualTest);
				case6.removeItemFromQuestionNum(case6.getNumOfQuestion());
				case6.addToCurrentNumOfQuestions();
				if (case6.getCurrentNumOfQuestions() <= case6.getNumOfQuestionsInTest()) {
					case6.setMessagePane(true);
				} else {
					try {
						module.saveTestToFile(manualTest);
					} catch (FileNotFoundException e1) {
						case6.setSaveTestErrorVisible();
						e1.printStackTrace();
					}
					case6.setCreatedTestPane(module.printTest(manualTest));
					module.testSortViaLengthOfAnswers(manualTest);
					case8.setTextListToWindow(module.showAllTests());
					case8.setComboBox(module.getNumOfTests());

				}
			}

		});
		case6.addMyEventHandlerToExit(e -> {
			if (case6.isTestCreatedAlready()) {
				module.subtractFromNumOfTests();
			}
			case6.clearStage();
			case6.close();
		});
		case7.addmyEventHandlerForOk(e -> {
			
			if(case7.getValueChosenNumOfQuestions()!=0) {
			Test automaticTest = module.createAutomaticTest(case7.getValueChosenNumOfQuestions());
			case7.setTextToWindow(module.printTest(automaticTest));
			

			try {
				module.saveTestToFile(automaticTest);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				case7.setTestFileErrorVisible();

			}
			case7.setTestCreatedSuccessfully();
			case8.setTextListToWindow(module.showAllTests());
			case8.setComboBox(module.getNumOfTests());
		}});
		
		case8.addToMyEventHandlerForComboBox(e -> {
			if (case8.getValueOfComboBox() != 0) {
			case8.setTextChosenTestToWindow(module.printTest(module.getTests().get(case8.getValueOfComboBox()-1)));
			case8.changeToConfirmationWindow(true);
			}
		});
		
		case8.addToMyEventHandlerForOk(e -> {
			try {
				module.createTestViaCopy(module.getTests().get(case8.getValueOfComboBox()-1));
				case8.setCopied();
			} catch (CloneNotSupportedException e1) {
				case8.setError();
				e1.printStackTrace();
			}
		});
		
		
        menu.addMyEventHandlerForCase9(e->{
        	CommandCase9 cmd = new CommandCase9(module,menu);
        	cmd.execute();
        	//module.transferTestsToTreeSet();
        	//menu.setCase10Disable(false);
        	
        });
        menu.addMyEventHandlerForCase10(e->{
        	CommandCase10 cmd = new CommandCase10(module,menu);
        	cmd.execute();
        	//module.transferTestsToLinkedHashSet();
        	//menu.setCase11Disable(false);
        });
        menu.addMyEventHandlerForCase11(e->{
        	case11.showWindow();
        	
        	//so you could see the implementation of the observers
        	try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
        	module.transferTestsFromHashLinkedSetToMyArrayList();
        	module.getMyArray().attach(case11.getMyButton());
        	module.getMyArray().attach(case11.getLabel());
        	it = module.activateIterator();
    
        });
        case11.addMyEventHandlerToMyButton(e->{
        	module.printMyArrayList(it);
        });
        menu.addMyEventHandlerForCase12(e->{
        	if(module.creatMementoToQuestions())
        		menu.setCase13Disable(false);
        });
        menu.addMyEventHandlerForCase13(e->{
        	module.retriveMementoToQuestions();
        });
		menu.addMyEventHandlerForCase14(e -> {
			try {
				menu.setExitText3Visibility(false);
				menu.setExitText4Visibility(false);
				module.savePoolOfQuestionsAndTestsToFile();
				menu.setExitText1Visibility(true);
				menu.setStackPaneVisible();
			} catch (IOException e1) {
				menu.setExitText2Visibility(true);
				menu.setStackPaneVisible();
				e1.printStackTrace();

			}

		});

	}

}
