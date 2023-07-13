package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import module.AmericanQuestions;
import module.Answers;
import module.OpenQuestions;
import module.OperatingSystem;
import module.OperatingSystemModule;
import module.Query;
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
import view.SignIn;

public class Controller {
	private Menu menu;
	//private ArrayList<OperatingSystemModule> moduleList;
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
	private SignIn signIn;
	private AmericanQuestions question;
	private Test test;
	private Iterator<Test> it;
	private Query connection;

	public Controller(Menu menu, Case1 case1, Case2 case2, Case3 case3, Case4 case4, Case5 case5, Case6 case6,
			Case7 case7, Case8 case8, Case11 case11, SignIn signIn) throws ClassNotFoundException {
		this.menu = menu;
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
			this.connection = new Query();
		} catch (ClassNotFoundException | SQLException e3) {
			System.out.println("Problem creating connection with Data base");
			e3.printStackTrace();
		}
	
		/*	this.moduleList = new ArrayList<OperatingSystemModule>();
		 * try { this.moduleList =
		 * OperatingSystem.loadPoolOfQuestionsAndTestsFromFile(); this.module =
		 * moduleList.get(0);
		 * 
		 * menu.setExitText3Visibility(true); menu.setStackPaneVisible();
		 * menu.showStage(); } catch (ClassNotFoundException | IOException e1) {
		 * menu.setExitText4Visibility(true); menu.setStackPaneVisible();
		 * menu.showStage();
		 * 
		 * this.module = new OperatingSystemModule(123); try {
		 * module.addModuleToDB(connection); } catch (SQLException e2) { // TODO
		 * Auto-generated catch block e2.printStackTrace(); }
		 * 
		 * moduleList.add(module); e1.printStackTrace(); }
		 */

		signIn.addmyEventHandlerForOkSignUp(e -> {
			try {
				if (signIn.getPassword().getText().isBlank())
					signIn.setBlankPassErrorVisible();

				else if (!signIn.getPassword().getText().matches("[0-9]+"))
					signIn.setOnlyNumErrorVisible();

				else if (signIn.getPassword().getText().length() > 8) {
					signIn.setTooManyNumErrorErrorVisible();

				} else if (OperatingSystemModule.checkIfPassIsTaken(connection,
						Integer.parseInt(signIn.getPassword().getText()))) {
					signIn.setPassTakenErrorVisible();
				} else {

					this.module = new OperatingSystemModule(Integer.parseInt(signIn.getPassword().getText()));
					module.addModuleToDB(connection);

					module.constructOpenQuestion("how many notes does the major scale has?",
							module.constructAnswer("7", true));
					module.constructOpenQuestion("what scale is considered the scale of shabat by the jewish religion?",
							module.constructAnswer("hijaz", true));
					module.constructOpenQuestion("what scale is considered the scale of shabat by the jewish religion?",
							module.constructAnswer("hijaz", true));
					AmericanQuestions exampleQuestion = module
							.contructAmericanQuestion("what kind of scales are used in japanese traditional music?");
					module.contructAnswerForAmerican(exampleQuestion, "hexatonic scales", false);
					module.contructAnswerForAmerican(exampleQuestion, "heptatonic scales", false);
					module.contructAnswerForAmerican(exampleQuestion, "chromatic scales", false);
					module.contructAnswerForAmerican(exampleQuestion, "pentatonic scales", true);
					AmericanQuestions scales = module.contructAmericanQuestion("what is the origin of blues?");
					module.contructAnswerForAmerican(scales, "african music", false);
					module.contructAnswerForAmerican(scales,
							"african rythms and scales combined with classical instruments", true);
					module.contructAnswerForAmerican(scales, "make america great again", false);
					module.contructAnswerForAmerican(scales, "country music combined with classical composition",
							false);
					module.contructAnswerForAmerican(scales, "south american folkore", false);
					module.contructAnswerForAmerican(scales, "gypsy music", false);

					for (int i = 0; i < module.getQuestions().size(); i++) {
						if (module.getQuestion(i) instanceof AmericanQuestions) {
							try {
								module.addAmericanQuestionToDB(connection, (AmericanQuestions) module.getQuestion(i));
							} catch (SQLException e1) { // e1.printStackTrace();
							}
						} else {
							try {
								module.addOpenQuestionToDB(connection, (OpenQuestions) module.getQuestion(i));
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					}
					signIn.closeWindow();
					menu.showMenu();
				}
			} catch (NumberFormatException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		signIn.addmyEventHandlerForOkSignIn(e -> {
			try {
				if (signIn.getPasswordSignIn().getText().isBlank())
					signIn.setWrongPassErrorVisible();
				else if (!signIn.getPasswordSignIn().getText().matches("[0-9]+"))
					signIn.setWrongPassErrorVisible();

				else if (signIn.getPasswordSignIn().getText().length() > 8) {
					signIn.setWrongPassErrorVisible();

				} else if (!OperatingSystemModule.checkIfPassIsTaken(connection,
						Integer.parseInt(signIn.getPasswordSignIn().getText()))) {
					signIn.setWrongPassErrorVisible();

				} else {
					this.module = OperatingSystemModule.retrieveModule(connection,
							Integer.parseInt(signIn.getPasswordSignIn().getText()));
					signIn.closeWindow();

					menu.showMenu();
				}
			} catch (NumberFormatException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
	

		menu.addMyEventHandlerForCase1(e -> {

			try {
				case1.addTextToWindow(module.showAllQuestions(connection));
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			case1.showWindow();

		});

		menu.addMyEventHandlerForCase2(e -> {
			case2.showWindow();

		});

		menu.addMyEventHandlerForCase3(e -> {
			try {
				case3.addTextToWindow(module.showAllQuestions(connection));
				case3.setComboBox(module.getQuestionsSerial(connection));
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			case3.showWindow();

		});
		menu.addMyEventHandlerForCase4(e -> {
			try {
				case4.addTextToWindow(module.showAllQuestions(connection));
				case4.setComboBoxQuestion(module.getQuestionsSerial(connection));
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			case4.showWindow();

		});
		menu.addMyEventHandlerForCase5(e -> {
			try {
				case5.addTextToWindow(module.showAllQuestions(connection));
				case5.setComboBoxQuestion(module.getQuestionsSerial(connection));
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			case5.showWindow();

		});
		menu.addMyEventHandlerForCase6(e -> {
			try {
				case6.setComboBox(module.getQuestionsSerial(connection));
				case6.addTextToWindow(module.showAllQuestions(connection));
				case6.setNumberOfQuestionsInDataBase(module.getNumOfQuestionsInDB(connection));
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			case6.showWindow();

		});
		menu.addMyEventHandlerForCase7(e -> {
			try {
				case7.setComboBox(module.getNumOfQuestionsInDB(connection));

			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			case7.showWindow();

		});

		menu.addMyEventHandlerForCase8(e -> {
			try {
				case8.setTextListToWindow(module.showTestSerials(connection));
				case8.setComboBox(module.getTestSerials(connection));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			case8.showWindow();

		});

		case2.addMyEventHandlerOk(e -> {

			if (case2.getQuestionTextField().getText().isEmpty() || case2.getAnswerTextFieldArrayEmpty()) {
				if (case2.getQuestionTextField().getText().isEmpty()) {
					case2.setQuestionBlankErrorVisible(true);
				} else {
					case2.setAnswerBlankErrorvisible(true);
				}
			} else {
				if (case2.getRadioButton1().isSelected()) {
					try {
						if (module.checkIfOpenQuestionExistInDB(connection, case2.getQuestionTextField().getText())) {
							case2.getQuestionTextError().setVisible(true);
						} else {
							OpenQuestions question = module.constructOpenQuestion(
									case2.getQuestionTextField().getText(),
									module.constructAnswer(case2.getAnswerTextFieldArray().get(0).getText(), true));
							/*
							 * if (module.checkIfQuestionExistViaQuestion(question)) {
							 * case2.getQuestionTextError().setVisible(true);
							 * 
							 * } else {
							 */
							case2.changeOkVisiblity(false, true);
							case2.changeborderPaneScene();
							case1.addTextToWindow(module.showAllQuestions(connection));
							case3.addTextToWindow(module.showAllQuestions(connection));
							case4.addTextToWindow(module.showAllQuestions(connection));
							case5.addTextToWindow(module.showAllQuestions(connection));
							case6.addTextToWindow(module.showAllQuestions(connection));
							case3.addToComboBox(question.getSerialNum());
							case4.addToComboBoxQuestion(question.getSerialNum());
							case5.addToComboBoxQuestion(question.getSerialNum());
							case6.addToComboBox(question.getSerialNum());
							module.addOpenQuestionToDB(connection, question);
							case6.setNumberOfQuestionsInDataBase(module.getNumOfQuestionsInDB(connection));
							case7.setComboBox(module.getNumOfQuestionsInDB(connection));

						}
					}
					/* } */ catch (SQLException e1) {

						e1.printStackTrace();
					}
				} else if (case2.getRadioButton2().isSelected()) {
					try {
						if (module.checkIfAmericanQuestionExistInDB(connection,
								case2.getQuestionTextField().getText())) {
							case2.getQuestionTextError().setVisible(true);
						} else {

							boolean sameAnswerTwice = false;
							int fAnswer = 0;
							int sAnswer = 0;

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
								boolean answerFieldBlank = false;
								for (int k = 0; k < case2.getSelectedIndex(); k++) {

									if (case2.getAnswerTextFieldArray().get(k).getText().isBlank())
										answerFieldBlank = true;
								}
								if (answerFieldBlank) {
									case2.setNotEnoughAnswersErrorVisible(true);
								} else {
									question = module.contructAmericanQuestion(case2.getQuestionTextField().getText());
									//
									for (int h = 0; h < case2.getSelectedIndex(); h++) {
										module.contructAnswerForAmerican(question,
												case2.getAnswerTextFieldArray().get(h).getText(),
												case2.getcBoxArray().get(h).isSelected());
									}

									case2.changeOkVisiblity(false, true);
									case2.changeborderPaneScene();
									case1.addTextToWindow(module.showAllQuestions(connection));
									case3.addTextToWindow(module.showAllQuestions(connection));
									case4.addTextToWindow(module.showAllQuestions(connection));
									case5.addTextToWindow(module.showAllQuestions(connection));
									case6.addTextToWindow(module.showAllQuestions(connection));
									case3.addToComboBox(question.getSerialNum());
									case4.addToComboBoxQuestion(question.getSerialNum());
									case5.addToComboBoxQuestion(question.getSerialNum());
									case6.addToComboBox(question.getSerialNum());
									case6.setNumberOfQuestionsInDataBase(module.getNumOfQuestionsInDB(connection));
									case7.setComboBox(module.getNumOfQuestionsInDB(connection));
									module.addAmericanQuestionToDB(connection, question);
								}
							}
						}
					}
					/* } */ catch (SQLException e1) {

						e1.printStackTrace();
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
		case2.addMyEventHandlerForClose(e -> {
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
					try {
						if (module.checkIfQuestionExistInWithSerialNumDB(connection, case3.getNewWording().getText(),
								case3.getQuestionNum().getValue(), module.getSerialNum())) {
							case3.setNewWordingAlreadyExistsErrorVisible(true);
						} else {
							/*
							 * module.changeBodyOfQuestion(case3.getQuestionNum().getValue() - 1,
							 * case3.getNewWording().getText()); if (module.checkIfQuestionExistViaText(
							 * module.getQuestion(case3.getQuestionNum().getValue() - 1))) {
							 * case3.setNewWordingAlreadyExistsErrorVisible(true); } else {
							 */
							case3.changeborderPaneScene();
							case1.addTextToWindow(module.showAllQuestions(connection));
							case3.addTextToWindow(module.showAllQuestions(connection));
							case4.addTextToWindow(module.showAllQuestions(connection));
							case5.addTextToWindow(module.showAllQuestions(connection));
							case6.addTextToWindow(module.showAllQuestions(connection));
							module.updateQuestionInDB(connection, case3.getQuestionNum().getValue(),
									case3.getNewWording().getText());
						}
					}
					/* } */catch (SQLException e1) {

						e1.printStackTrace();
					}

				}
			}
		});
		case3.addMyEventHandlerOk2(e -> {
			case3.resetStage();
			case3.close();

		});
		case3.addMyEventHandlerForClose(e -> {
			case3.resetStage();
			case3.close();
		});
		case4.addMyEventHandlerForQuestionComboBox(e -> {

			try {
				if (case4.getQuestionNum().getValue() != null
						&& !module.checkIfQuestionOpen(connection, case4.getQuestionNum().getValue())) {
					case4.getAnswerNum().getItems().clear();
					case4.setComboBoxAnswer(module.getAnswersSerial(connection, case4.getQuestionNum().getValue()));
					case4.setAnswerNumberVisible(true);
					case4.setAnswerNumVisible(true);
				} else {
					case4.setAnswerNumberVisible(false);
					case4.setAnswerNumVisible(false);
				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		});
		case4.addMyEventHandlerForOk1(e -> {
			if (case4.getQuestionNum().getValue() == null) {
				case4.setQuestionNumberErrorVisible(true);
			} else
				try {
					if (case4.getAnswerNum().getValue() == null
							&& !module.checkIfQuestionOpen(connection, case4.getQuestionNum().getValue())) {
						case4.setAnswerNumberErrorVisible(true);
					} else if (case4.getAnswerTextField().getText().isEmpty()) {
						case4.setAnswerTextEmptyErrorVisible(true);
					} else if (!module.checkIfQuestionOpen(connection, case4.getQuestionNum().getValue())
							&& module.checkIfAnswerTextExist(connection, case4.getQuestionNum().getValue(),
									case4.getAnswerTextField().getText())) {
						case4.setAnswerWithSameTextErrorVisible(true);

					} else {
						if (case4.getAnswerNum().getValue() == null) {
							
							module.updateAnswerOfOpenInDB(connection, case4.getQuestionNum().getValue(),case4.getAnswerTextField().getText());
							/*module.updateAnswerInDB(connection, case4.getAnswerNum().getValue(),
									case4.getAnswerTextField().getText());*/

							case4.changeborderPaneScene();
							try {

								case1.addTextToWindow(module.showAllQuestions(connection));
								case3.addTextToWindow(module.showAllQuestions(connection));
								case4.addTextToWindow(module.showAllQuestions(connection));
								case5.addTextToWindow(module.showAllQuestions(connection));
								case6.addTextToWindow(module.showAllQuestions(connection));

							} catch (SQLException e1) {

								e1.printStackTrace();
							}

						} else {
							module.updateAnswerInDB(connection, case4.getAnswerNum().getValue(),
									case4.getAnswerTextField().getText());
							case4.changeborderPaneScene();
							try {
								case1.addTextToWindow(module.showAllQuestions(connection));
								case3.addTextToWindow(module.showAllQuestions(connection));
								case4.addTextToWindow(module.showAllQuestions(connection));
								case5.addTextToWindow(module.showAllQuestions(connection));
								case6.addTextToWindow(module.showAllQuestions(connection));
							} catch (SQLException e1) {

								e1.printStackTrace();
							}

						}
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

		});
		case4.addMyEventHandlerOk2(e -> {
			case4.resetStage();
			case4.close();

		});
		case4.addMyEventHandlerForClose(e -> {
			case4.resetStage();
			case4.close();
		});
		case5.addMyEventHandlerForQuestionComboBox(e -> {
			case5.getAnswerNum().getItems().clear();
			if (case5.getQuestionNum().getValue() != null) {
				try {
					if (module.checkIfQuestionOpen(connection, case5.getQuestionNum().getValue())) {
						case5.setOpenQuestionErrorVisible(true);
					} else {

						case5.setComboBoxAnswer(module.getAnswersSerial(connection, case5.getQuestionNum().getValue()));

					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});

		case5.addMyEventHandlerForOk1(e -> {
			if (case5.getQuestionNum().getValue() == null) {
				case5.setQuestionNumberErrorVisible(true);
			} else
				try {
					if (module.checkIfQuestionOpen(connection, case5.getQuestionNum().getValue())) {
						case5.setOpenQuestionErrorVisible(true);
					} else if (case5.getAnswerNum().getValue() == null) {
						case5.setAnswerNumberErrorVisible(true);
					} else if (!module.checkIfQuestionHasMoreThanFourAns(connection,
							case5.getQuestionNum().getValue())) {
						case5.setAtLeastFourErrorVisible(true);
					} else {
						module.deleteAnswerFromDB(connection, case5.getQuestionNum().getValue(),
								case5.getAnswerNum().getValue());
						case5.changeborderPaneScene();

						case1.addTextToWindow(module.showAllQuestions(connection));
						case3.addTextToWindow(module.showAllQuestions(connection));
						case4.addTextToWindow(module.showAllQuestions(connection));
						case5.addTextToWindow(module.showAllQuestions(connection));
						case6.addTextToWindow(module.showAllQuestions(connection));

					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
		});
		case5.addMyEventHandlerOk2(e -> {
			case5.resetStage();
			case5.close();

		});
		case5.addMyEventHandlerForClose(e -> {
			case5.resetStage();
			case5.close();
		});
		case6.addMyEvenHandlerForQuestionNumComboBox(e -> {
			case6.clearBottomCenterPane();
			case6.clearNumOfAnswers();
			try {
				if (case6.getNumOfQuestion() != 0
						&& !module.checkIfQuestionOpen(connection, case6.getNumOfQuestion())) {
					case6.setChooseNumOfAnswers(true);
					case6.setNumOfAnswers(true);
					case6.setValuesfornumOfAnswersForMainBox(
							module.getNumOfAnswersForQuestionInDB(connection, case6.getNumOfQuestion()) - 2,
							module.getAnswersSerial(connection, case6.getNumOfQuestion()));

				} else {
					case6.setChooseNumOfAnswers(false);
					case6.setNumOfAnswers(false);
				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		});
		
		case6.addMyEventHandlerForRestore(e->{
			try {
				case6.setValuesfornumOfAnswersForBoxArray(module.getAnswersSerial(connection, case6.getNumOfQuestion()));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			case6.setRestoreVisible(false);
		});
		
		case6.addMyEvenHandlerForOk1(e -> {

			if (case6.getNumOfQuestionsInTest() != 0 && case6.getCurrentNumOfQuestions() == 1) {
				test = module.createTest(case6.getNumOfQuestionsInTest());
				try {
					module.addTestToDB(connection, test);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
				case6.setTestCreatedAlready(true);
			}
			if (case6.isNumOfQuestionsInTestVisible() && case6.getNumOfQuestionsInTest() == 0) {
				case6.setNumberOfQuestionsErrorVisible();
			} else if (case6.isNumOfQuestionVisible() && case6.getNumOfQuestion() == 0) {
				case6.setQuestionErrorVisible();
			} else
				try {
					if (!module.checkIfQuestionOpen(connection, case6.getNumOfQuestion())) {
						if (case6.getNumOfAnswers() == 0) {
							case6.setNumberOfAnswersErrorVisible(true);
						} else if (case6.checkThatAllOptionsAreFilled()) {

							module.addAmericanQuestionToModuleHasTestAndQuestions(connection, test.getSerialNum(),
									case6.getNumOfQuestion(), case6.getCurrentNumOfQuestions(),
									case6.getNumOfAnswers());

							/*
							 * AmericanQuestions newQuestion = OperatingSystem.contructAmericanQuestion(
							 * (AmericanQuestions) module.getQuestion((case6.getNumOfQuestion() - 1)),
							 * case6.getNumOfAnswers() + 2);
							 */

							for (int i = 0; i < case6.getNumOfAnswers(); i++) {

								module.addAnswerToModuleHasTestsAndAns(connection, test.getSerialNum(),
										case6.getNumOfQuestion(), case6.getValueOfAnswerBox(i), i + 1);
								/*
								 * module.contructAnswerForAmerican(newQuestion, module.getAnswer(
								 * ((AmericanQuestions) module.getQuestion((case6.getNumOfQuestion() - 1))),
								 * case6.getValueOfAnswerBox(i)).getBodyOfAnswer(), module.getAnswer(
								 * ((AmericanQuestions) module .getQuestion((case6.getNumOfQuestion() - 1))),
								 * case6.getValueOfAnswerBox(i)).isCorrect());
								 */

							}
							// module.verifyAnswers(newQuestion);
							// module.addQuestionToTest(newQuestion, manualTest);
							module.verifyCorrectnessOfAnswersInTest(connection, test.getSerialNum(),
									case6.getNumOfQuestion());
							case6.removeItemFromQuestionNum(case6.getNumOfQuestion());
							case6.addToCurrentNumOfQuestions();
							if (case6.getCurrentNumOfQuestions() <= case6.getNumOfQuestionsInTest()) {
								case6.setMessagePane(true);
							} else {
								/*
								 * try { //module.saveTestToFile(test); } catch (FileNotFoundException e1) {
								 * case6.setSaveTestErrorVisible(); e1.printStackTrace(); }
								 */
								case6.setCreatedTestPane(module.showTest(connection, test.getSerialNum(), false));
								// module.testSortViaLengthOfAnswers(test);
								// case8.setTextListToWindow(module.showAllTests());
								// case8.setComboBox(module.getNumOfTests());
							}
						}
					} else {

						module.addOpenQuestionToModuleHasTestAndQuestions(connection, test.getSerialNum(),
								case6.getNumOfQuestion(), case6.getCurrentNumOfQuestions());
						// module.addQuestionToTest(module.getQuestion((case6.getNumOfQuestion() - 1)),
						// manualTest);
						case6.removeItemFromQuestionNum(case6.getNumOfQuestion());
						case6.addToCurrentNumOfQuestions();
						if (case6.getCurrentNumOfQuestions() <= case6.getNumOfQuestionsInTest()) {
							case6.setMessagePane(true);
						} else {
							/*
							 * try { module.saveTestToFile(manualTest); } catch (FileNotFoundException e1) {
							 * case6.setSaveTestErrorVisible(); e1.printStackTrace(); }
							 */
							case6.setCreatedTestPane(module.showTest(connection, test.getSerialNum(), false));
							// module.testSortViaLengthOfAnswers(test);

						}
					}
				} catch (SQLException e1) {
					try {
						module.deleteTest(connection, test.getSerialNum());
					} catch (SQLException e2) {

						e2.printStackTrace();
					}
					e1.printStackTrace();
				}

		});
	
		case6.addMyEventHandlerToExit(e -> {
			if (case6.isTestCreatedAlready()) {
				try {
					module.deleteTest(connection, module.getTestCounter() - 1);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
			case6.clearStage();
			case6.close();
		});
		case6.addMyEventHandlerForClose(e -> {
			if (case6.isTestCreatedAlready()) {
				try {
					module.deleteTest(connection, module.getTestCounter() - 1);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
			case6.clearStage();
			case6.close();
		});
		case7.addmyEventHandlerForOk(e -> {

			if (case7.getValueChosenNumOfQuestions() != 0) {
				test = module.createTest(case7.getValueChosenNumOfQuestions());
				try {
					module.addTestToDB(connection, test);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
				try {
					module.addRandomQuestionsToTest(connection, test.getSerialNum(),
							case7.getValueChosenNumOfQuestions());
				} catch (SQLException e1) {
					try {
						module.deleteTest(connection, test.getSerialNum());
					} catch (SQLException e2) {

						e2.printStackTrace();
					}
					e1.printStackTrace();
				}

				try {
					case7.setTextToWindow(module.showTest(connection, test.getSerialNum(), false));
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

				/*
				 * try { module.saveTestToFile(automaticTest); } catch (FileNotFoundException
				 * e1) { e1.printStackTrace(); case7.setTestFileErrorVisible();
				 * 
				 * }
				 */
				case7.setTestCreatedSuccessfully();
				// case8.setTextListToWindow(module.showAllTests());

			}
		});

		case8.addToMyEventHandlerForComboBox(e -> {
			if (case8.getValueOfComboBox() != 0) {
				try {
					case8.setTextChosenTestToWindow(module.showTest(connection, case8.getValueOfComboBox(), false));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				case8.changeToConfirmationWindow(true);
			}
		});

		case8.addToMyEventHandlerForOk(e -> {
			try {
				module.saveTestToFile(module.showTest(connection, case8.getValueOfComboBox(), true),
						module.showTest(connection, case8.getValueOfComboBox(), false));
			} catch (FileNotFoundException | SQLException e1) {
				case8.changeToConfirmationWindow(false);
				e1.printStackTrace();
			}
			case8.setCopied();

		});

		menu.addMyEventHandlerForCase12(e -> {
			connection.closeConnection();
			menu.closeStage();

			/*
			 * menu.setExitText3Visibility(false); menu.setExitText4Visibility(false);
			 * module.savePoolOfQuestionsAndTestsToFile(moduleList);
			 * menu.setExitText1Visibility(true); menu.setStackPaneVisible();
			 * 
			 * menu.setExitText2Visibility(true); menu.setStackPaneVisible();
			 */

		});
		/*
		 * menu.addMyEventHandlerForClose(e -> {
		 * 
		 * menu.setExitText3Visibility(false); menu.setExitText4Visibility(false); //
		 * module.savePoolOfQuestionsAndTestsToFile(moduleList);
		 * menu.setExitText1Visibility(true); menu.setStackPaneVisible();
		 * 
		 * menu.setExitText2Visibility(true); menu.setStackPaneVisible();
		 * 
		 * 
		 * 
		 * });
		 */

	}

}
