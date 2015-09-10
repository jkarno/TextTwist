import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

// Initializes images so that they only are read into the program once.
// The images are then mapped to a String, Image map so that they can 
public class readImagesToMap {

	public static Map<String, Image> images = new HashMap<String, Image>();
	
	public readImagesToMap() {	
		try {
        	BufferedImage letterA = ImageIO.read(new File("letterPiece_A.png"));
        	BufferedImage letterB = ImageIO.read(new File("letterPiece_B.png"));
        	BufferedImage letterC = ImageIO.read(new File("letterPiece_C.png"));
        	BufferedImage letterD = ImageIO.read(new File("letterPiece_D.png"));
        	BufferedImage letterE = ImageIO.read(new File("letterPiece_E.png"));
        	BufferedImage letterF = ImageIO.read(new File("letterPiece_F.png"));
        	BufferedImage letterG = ImageIO.read(new File("letterPiece_G.png"));
        	BufferedImage letterH = ImageIO.read(new File("letterPiece_H.png"));
        	BufferedImage letterI = ImageIO.read(new File("letterPiece_I.png"));
        	BufferedImage letterJ = ImageIO.read(new File("letterPiece_J.png"));
        	BufferedImage letterK = ImageIO.read(new File("letterPiece_K.png"));
        	BufferedImage letterL = ImageIO.read(new File("letterPiece_L.png"));
        	BufferedImage letterM = ImageIO.read(new File("letterPiece_M.png"));
        	BufferedImage letterN = ImageIO.read(new File("letterPiece_N.png"));
        	BufferedImage letterO = ImageIO.read(new File("letterPiece_O.png"));
        	BufferedImage letterP = ImageIO.read(new File("letterPiece_P.png"));
        	BufferedImage letterQ = ImageIO.read(new File("letterPiece_Q.png"));
        	BufferedImage letterR = ImageIO.read(new File("letterPiece_R.png"));
        	BufferedImage letterS = ImageIO.read(new File("letterPiece_S.png"));
        	BufferedImage letterT = ImageIO.read(new File("letterPiece_T.png"));
        	BufferedImage letterU = ImageIO.read(new File("letterPiece_U.png"));
        	BufferedImage letterV = ImageIO.read(new File("letterPiece_V.png"));
        	BufferedImage letterW = ImageIO.read(new File("letterPiece_W.png"));
        	BufferedImage letterX = ImageIO.read(new File("letterPiece_X.png"));
        	BufferedImage letterY = ImageIO.read(new File("letterPiece_Y.png"));
        	BufferedImage letterZ = ImageIO.read(new File("letterPiece_Z.png"));
        	
        	images.put("a", letterA);
        	images.put("b", letterB);
        	images.put("c", letterC);
        	images.put("d", letterD);
        	images.put("e", letterE);
        	images.put("f", letterF);
        	images.put("g", letterG);
        	images.put("h", letterH);
        	images.put("i", letterI);
        	images.put("j", letterJ);
        	images.put("k", letterK);
        	images.put("l", letterL);
        	images.put("m", letterM);
        	images.put("n", letterN);
        	images.put("o", letterO);
        	images.put("p", letterP);
        	images.put("q", letterQ);
        	images.put("r", letterR);
        	images.put("s", letterS);
        	images.put("t", letterT);
        	images.put("u", letterU);
        	images.put("v", letterV);
        	images.put("w", letterW);
        	images.put("x", letterX);
        	images.put("y", letterY);
        	images.put("z", letterZ);
        	
        	// read GUI Items
        	BufferedImage GUI_BG = ImageIO.read
        			(new File("TextTwist_GUI_BG.png"));
        	images.put("GUI_BG", GUI_BG);
        	BufferedImage GUI_tileHolder = ImageIO.read
        			(new File("GUI_tileHolder.png"));
        	images.put("GUI_tileHolder", GUI_tileHolder);
        	
        	// buttons
        	BufferedImage BUTTON_nextRoundSelected = ImageIO.read
        			(new File("Button_nextRoundSelected.png"));
        	images.put("BUTTON_nextRoundSelected", BUTTON_nextRoundSelected);
        	BufferedImage BUTTON_nextRoundRegular = ImageIO.read
        			(new File("Button_nextRoundRegular.png"));
        	images.put("BUTTON_nextRoundRegular", BUTTON_nextRoundRegular);
        	BufferedImage BUTTON_menuRegular = ImageIO.read
        			(new File("Button_MenuRegular.png"));
        	images.put("BUTTON_menuRegular", BUTTON_menuRegular);
        	BufferedImage BUTTON_menuSelected = ImageIO.read
        			(new File("Button_MenuSelected.png"));
        	images.put("BUTTON_menuSelected", BUTTON_menuSelected);
        	
        	BufferedImage BUTTON_ShuffleClick = ImageIO.read
        			(new File("Button_ShuffleClick.png"));
        	images.put("BUTTON_ShuffleClick", BUTTON_ShuffleClick);
        	BufferedImage BUTTON_ShuffleHover = ImageIO.read
        			(new File("BUTTON_ShuffleSelected2.png"));
        	images.put("BUTTON_ShuffleHover", BUTTON_ShuffleHover);
        	BufferedImage BUTTON_ShuffleRegular = ImageIO.read
        			(new File("BUTTON_ShuffleReg2.png"));
        	images.put("BUTTON_ShuffleRegular", BUTTON_ShuffleRegular);
        	
        	BufferedImage BUTTON_HintsClick1 = ImageIO.read
        			(new File("Button_HintsClicked1.png"));
        	images.put("BUTTON_HintsClick1", BUTTON_HintsClick1);
        	BufferedImage BUTTON_HintsClick2 = ImageIO.read
        			(new File("Button_HintsClicked2.png"));
        	images.put("BUTTON_HintsClick2", BUTTON_HintsClick2);
        	BufferedImage BUTTON_HintsClick3 = ImageIO.read
        			(new File("Button_HintsClicked3.png"));
        	images.put("BUTTON_HintsClick3", BUTTON_HintsClick3);
        	
        	BufferedImage BUTTON_HintsSelected1 = ImageIO.read
        			(new File("Button_HintsSelected1.png"));
        	images.put("BUTTON_HintsSelected1", BUTTON_HintsSelected1);
        	BufferedImage BUTTON_HintsSelected2 = ImageIO.read
        			(new File("Button_HintsSelected2.png"));
        	images.put("BUTTON_HintsSelected2", BUTTON_HintsSelected2);
        	BufferedImage BUTTON_HintsSelected3 = ImageIO.read
        			(new File("Button_HintsSelected3.png"));
        	images.put("BUTTON_HintsSelected3", BUTTON_HintsSelected3);
        	
        	BufferedImage BUTTON_HintsRegular1 = ImageIO.read
        			(new File("Button_HintsRegular1.png"));
        	images.put("BUTTON_HintsRegular1", BUTTON_HintsRegular1);
        	BufferedImage BUTTON_HintsRegular2 = ImageIO.read
        			(new File("Button_HintsRegular2.png"));
        	images.put("BUTTON_HintsRegular2", BUTTON_HintsRegular2);
        	BufferedImage BUTTON_HintsRegular3 = ImageIO.read
        			(new File("Button_HintsRegular3.png"));
        	images.put("BUTTON_HintsRegular3", BUTTON_HintsRegular3);
        	
        	BufferedImage BUTTON_HintsUsed = ImageIO.read
        			(new File("Button_HintsUsed.png"));
        	images.put("BUTTON_HintsUsed", BUTTON_HintsUsed);
        	
        	// Errors
        	BufferedImage ERROR_AlreadyEntered = ImageIO.read
        			(new File("ERROR_AlreadyEntered.png"));
        	images.put("ERROR_AlreadyEntered", ERROR_AlreadyEntered);
        	BufferedImage ERROR_NotInDict = ImageIO.read
        			(new File("ERROR_NotInDict.png"));
        	images.put("ERROR_NotInDict", ERROR_NotInDict);
        	
        	// Bonuses
        	BufferedImage BONUS_AllWords = ImageIO.read
        			(new File("BONUS_AllWords.png"));
        	images.put("BONUS_AllWords", BONUS_AllWords);
        	BufferedImage BONUS_DoublePoints = ImageIO.read
        			(new File("BONUS_DoublePoints.png"));
        	images.put("BONUS_DoublePoints", BONUS_DoublePoints);
        	
        	// Game Over
        	BufferedImage GameOverScreen = ImageIO.read
        			(new File("GameOver_Background.png"));
        	images.put("GameOverScreen", GameOverScreen);
        	BufferedImage GameOverSubmitReg = ImageIO.read
        			(new File("GameOver_SubmitRegular.png"));
        	images.put("GameOverSubmitReg", GameOverSubmitReg);
        	BufferedImage GameOverSubmitSel = ImageIO.read
        			(new File("GameOver_SubmitSelected.png"));
        	images.put("GameOverSubmitSel", GameOverSubmitSel);
        	BufferedImage GameOverScore0 = ImageIO.read
        			(new File("GameOver0.png"));
        	images.put("GameOverScore0", GameOverScore0);
        	BufferedImage tryAgain = ImageIO.read
        			(new File("Button_PlayAgain.png"));
        	images.put("tryAgain", tryAgain);
        	BufferedImage tryAgainSel = ImageIO.read
        			(new File("Button_PlayAgainSel.png"));
        	images.put("tryAgainSel", tryAgainSel);
        	
        	// Leaderboard
        	BufferedImage LeaderBoardBG = ImageIO.read(
        			new File("LeaderBoardPage_GUI.png"));
        	images.put("LeaderBoardBG", LeaderBoardBG);
        	
        	// Instructions
        	BufferedImage Button_InsReg = ImageIO.read
        			(new File("instructionsButtonReg.png"));
        	images.put("Button_InsReg", Button_InsReg);
        	BufferedImage Button_InsSel = ImageIO.read
        			(new File("instructionsButtonSel.png"));
        	images.put("Button_InsSel", Button_InsSel);
        	BufferedImage instructions_BonusFeatures = ImageIO.read
        			(new File("instructions_BonusFeatures.png"));
        	images.put("instructions_BonusFeatures", instructions_BonusFeatures);
        	BufferedImage instructions_BonusFeatures2 = ImageIO.read
        			(new File("instructions_BonusFeatures2.png"));
        	images.put("instructions_BonusFeatures2", instructions_BonusFeatures2);
        	BufferedImage instructionsGameImp = ImageIO.read
        			(new File("InstructionsGameImp.png"));
        	images.put("instructionsGameImp", instructionsGameImp);
        	BufferedImage instructionsHowToPlay = ImageIO.read
        			(new File("instructionsHowToPlay.png"));
        	images.put("instructionsHowToPlay", instructionsHowToPlay);
        	BufferedImage Button_NextPage = ImageIO.read
        			(new File("Button_NextPage.png"));
        	images.put("Button_NextPage", Button_NextPage);
        	BufferedImage Button_NextPageSel = ImageIO.read
        			(new File("Button_NextPageSel.png"));
        	images.put("Button_NextPageSel", Button_NextPageSel);

        	
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	}
}

