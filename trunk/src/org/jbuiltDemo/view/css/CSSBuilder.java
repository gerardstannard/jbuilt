/*
 *   Copyright 2010 Gerard Stannard
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 */

/**
 * 
 * @author Gerard Stannard
 *
 */

package org.jbuiltDemo.view.css;

import java.util.Arrays;
import java.util.List;

import org.jbuilt.utils.JsfUtil;



public class CSSBuilder{
	
	public static String rules(String... rules){
		StringBuilder sb = new StringBuilder();
		for(String rule : rules){
			sb.append(rule);
		}
		return sb.toString();
	}
	

	
    public static String rule(String selector, String... declarationsOrComments){
        StringBuilder sb = new StringBuilder();
        sb.append(" \n ");
            sb.append(" \t\t").append( selector).append(" {\n");
            for(String declarationOrComment : declarationsOrComments){
                sb.append(" \t\t\t").append(declarationOrComment).append("\n ");
            }
            sb.append("\t}\n");
        return sb.toString();
    }
    
    public static  String comment(String comment){return new StringBuilder().append("/* ").append(comment).append(" */\n").toString();}
    
    public static  String selector(String... selector){
        StringBuilder sb = new StringBuilder();
        sb.append(" \n ");
            sb.append(" \t\t").append( selector).append(" {\n");
            for(String selectorPart : selector){
                sb.append(" ").append(selectorPart);
            }
            sb.append("\t}\n");
        return sb.toString();
    	}
    
    public static  String backgroundAttachment(String value){return new StringBuilder().append(backgroundAttachment).append(":").append(value).append(";\n").toString();}
    public static  String backgroundColor(String value){return new StringBuilder().append(backgroundColor).append(":").append(value).append(";\n").toString();}
    public static  String backgroundImage(String value){return new StringBuilder().append(backgroundImage).append(":").append(value).append(";\n").toString();}
    public static  String cssBackgroundImage(String value){return new StringBuilder().append(backgroundImage).append(":").append(url(value)).append(";\n").toString();}
    public static  String backgroundPosition(String value){return new StringBuilder().append(backgroundPosition).append(":").append(value).append(";\n").toString();}
    public static  String backgroundRepeat(String value){return new StringBuilder().append(backgroundRepeat).append(":").append(value).append(";\n").toString();}
    public static  String background(String value){return new StringBuilder().append(background).append(":").append(value).append(";\n").toString();}
    public static  String borderCollapse(String value){return new StringBuilder().append(borderCollapse).append(":").append(value).append(";\n").toString();}
    public static  String borderColor(String value){return new StringBuilder().append(borderColor).append(":").append(value).append(";\n").toString();}
    public static  String borderSpacing(String value){return new StringBuilder().append(borderSpacing).append(":").append(value).append(";\n").toString();}
    public static  String borderStyle(String value){return new StringBuilder().append(borderStyle).append(":").append(value).append(";\n").toString();}
    public static  String borderTop(String value){return new StringBuilder().append(borderTop).append(":").append(value).append(";\n").toString();}
    public static  String borderRight(String value){return new StringBuilder().append(borderRight).append(":").append(value).append(";\n").toString();}
    public static  String borderBottom(String value){return new StringBuilder().append(borderBottom).append(":").append(value).append(";\n").toString();}
    public static  String borderLeft(String value){return new StringBuilder().append(borderLeft).append(":").append(value).append(";\n").toString();}
    public static  String borderTopColor(String value){return new StringBuilder().append(borderTopColor).append(":").append(value).append(";\n").toString();}
    public static  String borderRightColor(String value){return new StringBuilder().append(borderRightColor).append(":").append(value).append(";\n").toString();}
    public static  String borderBottomColor(String value){return new StringBuilder().append(borderBottomColor).append(":").append(value).append(";\n").toString();}
    public static  String borderLeftColor(String value){return new StringBuilder().append(borderLeftColor).append(":").append(value).append(";\n").toString();}
    public static  String borderTopStyle(String value){return new StringBuilder().append(borderTopStyle).append(":").append(value).append(";\n").toString();}
    public static  String borderRightStyle(String value){return new StringBuilder().append(borderRightStyle).append(":").append(value).append(";\n").toString();}
    public static  String borderBottomStyle(String value){return new StringBuilder().append(borderBottomStyle).append(":").append(value).append(";\n").toString();}
    public static  String borderLeftStyle(String value){return new StringBuilder().append(borderLeftStyle).append(":").append(value).append(";\n").toString();}
    public static  String borderTopWidth(String value){return new StringBuilder().append(borderTopWidth).append(":").append(value).append(";\n").toString();}
    public static  String borderRightWidth(String value){return new StringBuilder().append(borderRightWidth).append(":").append(value).append(";\n").toString();}
    public static  String borderBottomWidth(String value){return new StringBuilder().append(borderBottomWidth).append(":").append(value).append(";\n").toString();}
    public static  String borderLeftWidth(String value){return new StringBuilder().append(borderLeftWidth).append(":").append(value).append(";\n").toString();}
    public static  String borderWidth(String value){return new StringBuilder().append(borderWidth).append(":").append(value).append(";\n").toString();}
    public static  String border(String value){return new StringBuilder().append(border).append(":").append(value).append(";\n").toString();}
    public static  String bottom(String value){return new StringBuilder().append(bottom).append(":").append(value).append(";\n").toString();}
    public static  String captionSide(String value){return new StringBuilder().append(captionSide).append(":").append(value).append(";\n").toString();}
    public static  String clear(String value){return new StringBuilder().append(clear).append(":").append(value).append(";\n").toString();}
    public static  String clip(String value){return new StringBuilder().append(clip).append(":").append(value).append(";\n").toString();}
    public static  String color(String value){return new StringBuilder().append(color).append(":").append(value).append(";\n").toString();}
    public static  String content(String value){return new StringBuilder().append(content).append(":").append(value).append(";\n").toString();}
    public static  String counterIncrement(String value){return new StringBuilder().append(counterIncrement).append(":").append(value).append(";\n").toString();}
    public static  String counterReset(String value){return new StringBuilder().append(counterReset).append(":").append(value).append(";\n").toString();}
    public static  String cueAfter(String value){return new StringBuilder().append(cueAfter).append(":").append(value).append(";\n").toString();}
    public static  String cueBefore(String value){return new StringBuilder().append(cueBefore).append(":").append(value).append(";\n").toString();}
    public static  String cue(String value){return new StringBuilder().append(cue).append(":").append(value).append(";\n").toString();}
    public static  String cursor(String value){return new StringBuilder().append(cursor).append(":").append(value).append(";\n").toString();}
    public static  String direction(String value){return new StringBuilder().append(direction).append(":").append(value).append(";\n").toString();}
    public static  String display(String value){return new StringBuilder().append(display).append(":").append(value).append(";\n").toString();}
    public static  String elevation(String value){return new StringBuilder().append(elevation).append(":").append(value).append(";\n").toString();}
    public static  String emptyCells(String value){return new StringBuilder().append(emptyCells).append(":").append(value).append(";\n").toString();}
    public static  String cssCssFloat(String value){return new StringBuilder().append(cssCssFloat).append(":").append(value).append(";\n").toString();}
    public static  String fontFamily(String value){return new StringBuilder().append(fontFamily).append(":").append(value).append(";\n").toString();}
    public static  String fontSize(String value){return new StringBuilder().append(fontSize).append(":").append(value).append(";\n").toString();}
    public static  String fontStyle(String value){return new StringBuilder().append(fontStyle).append(":").append(value).append(";\n").toString();}
    public static  String fontVariant(String value){return new StringBuilder().append(fontVariant).append(":").append(value).append(";\n").toString();}
    public static  String fontWeight(String value){return new StringBuilder().append(fontWeight).append(":").append(value).append(";\n").toString();}
    public static  String font(String value){return new StringBuilder().append(font).append(":").append(value).append(";\n").toString();}
    public static  String height(String value){return new StringBuilder().append(height).append(":").append(value).append(";\n").toString();}
    public static  String left(String value){return new StringBuilder().append(left).append(":").append(value).append(";\n").toString();}
    public static  String letterSpacing(String value){return new StringBuilder().append(letterSpacing).append(":").append(value).append(";\n").toString();}
    public static  String lineHeight(String value){return new StringBuilder().append(lineHeight).append(":").append(value).append(";\n").toString();}
    public static  String listStyleImage(String value){return new StringBuilder().append(listStyleImage).append(":").append(value).append(";\n").toString();}
    public static  String listStylePosition(String value){return new StringBuilder().append(listStylePosition).append(":").append(value).append(";\n").toString();}
    public static  String listStyleType(String value){return new StringBuilder().append(listStyleType).append(":").append(value).append(";\n").toString();}
    public static  String listStyle(String value){return new StringBuilder().append(listStyle).append(":").append(value).append(";\n").toString();}
    public static  String marginRight(String value){return new StringBuilder().append(marginRight).append(":").append(value).append(";\n").toString();}
    public static  String marginLeft(String value){return new StringBuilder().append(marginLeft).append(":").append(value).append(";\n").toString();}
    public static  String marginTop(String value){return new StringBuilder().append(marginTop).append(":").append(value).append(";\n").toString();}
    public static  String marginBottom(String value){return new StringBuilder().append(marginBottom).append(":").append(value).append(";\n").toString();}
    public static  String margin(String value){return new StringBuilder().append(margin).append(":").append(value).append(";\n").toString();}
    public static  String maxHeight(String value){return new StringBuilder().append(maxHeight).append(":").append(value).append(";\n").toString();}
    public static  String maxWidth(String value){return new StringBuilder().append(maxWidth).append(":").append(value).append(";\n").toString();}
    public static  String minHeight(String value){return new StringBuilder().append(minHeight).append(":").append(value).append(";\n").toString();}
    public static  String minWidth(String value){return new StringBuilder().append(minWidth).append(":").append(value).append(";\n").toString();}
    public static  String orphans(String value){return new StringBuilder().append(orphans).append(":").append(value).append(";\n").toString();}
    public static  String outlineColor(String value){return new StringBuilder().append(outlineColor).append(":").append(value).append(";\n").toString();}
    public static  String outlineStyle(String value){return new StringBuilder().append(outlineStyle).append(":").append(value).append(";\n").toString();}
    public static  String outlineWidth(String value){return new StringBuilder().append(outlineWidth).append(":").append(value).append(";\n").toString();}
    public static  String outline(String value){return new StringBuilder().append(outline).append(":").append(value).append(";\n").toString();}
    public static  String overflow(String value){return new StringBuilder().append(overflow).append(":").append(value).append(";\n").toString();}
    public static  String paddingTop(String value){return new StringBuilder().append(paddingTop).append(":").append(value).append(";\n").toString();}
    public static  String paddingRight(String value){return new StringBuilder().append(paddingRight).append(":").append(value).append(";\n").toString();}
    public static  String paddingBottom(String value){return new StringBuilder().append(paddingBottom).append(":").append(value).append(";\n").toString();}
    public static  String paddingLeft(String value){return new StringBuilder().append(paddingLeft).append(":").append(value).append(";\n").toString();}
    public static  String padding(String value){return new StringBuilder().append(padding).append(":").append(value).append(";\n").toString();}
    public static  String pageBreakAfter(String value){return new StringBuilder().append(pageBreakAfter).append(":").append(value).append(";\n").toString();}
    public static  String pageBreakBefore(String value){return new StringBuilder().append(pageBreakBefore).append(":").append(value).append(";\n").toString();}
    public static  String pageBreakInside(String value){return new StringBuilder().append(pageBreakInside).append(":").append(value).append(";\n").toString();}
    public static  String pauseAfter(String value){return new StringBuilder().append(pauseAfter).append(":").append(value).append(";\n").toString();}
    public static  String pauseBefore(String value){return new StringBuilder().append(pauseBefore).append(":").append(value).append(";\n").toString();}
    public static  String pause(String value){return new StringBuilder().append(pause).append(":").append(value).append(";\n").toString();}
    public static  String pitchRange(String value){return new StringBuilder().append(pitchRange).append(":").append(value).append(";\n").toString();}
    public static  String pitch(String value){return new StringBuilder().append(pitch).append(":").append(value).append(";\n").toString();}
    public static  String playDuring(String value){return new StringBuilder().append(playDuring).append(":").append(value).append(";\n").toString();}
    public static  String position(String value){return new StringBuilder().append(position).append(":").append(value).append(";\n").toString();}
    public static  String quotes(String value){return new StringBuilder().append(quotes).append(":").append(value).append(";\n").toString();}
    public static  String richness(String value){return new StringBuilder().append(richness).append(":").append(value).append(";\n").toString();}
    public static  String right(String value){return new StringBuilder().append(right).append(":").append(value).append(";\n").toString();}
    public static  String speakHeader(String value){return new StringBuilder().append(speakHeader).append(":").append(value).append(";\n").toString();}
    public static  String speakNumeral(String value){return new StringBuilder().append(speakNumeral).append(":").append(value).append(";\n").toString();}
    public static  String speakPunctuation(String value){return new StringBuilder().append(speakPunctuation).append(":").append(value).append(";\n").toString();}
    public static  String speak(String value){return new StringBuilder().append(speak).append(":").append(value).append(";\n").toString();}
    public static  String speechRate(String value){return new StringBuilder().append(speechRate).append(":").append(value).append(";\n").toString();}
    public static  String stress(String value){return new StringBuilder().append(stress).append(":").append(value).append(";\n").toString();}
    public static  String tableLayout(String value){return new StringBuilder().append(tableLayout).append(":").append(value).append(";\n").toString();}
    public static  String textAlign(String value){return new StringBuilder().append(textAlign).append(":").append(value).append(";\n").toString();}
    public static  String textDecoration(String value){return new StringBuilder().append(textDecoration).append(":").append(value).append(";\n").toString();}
    public static  String textIndent(String value){return new StringBuilder().append(textIndent).append(":").append(value).append(";\n").toString();}
    public static  String textTransform(String value){return new StringBuilder().append(textTransform).append(":").append(value).append(";\n").toString();}
    public static  String top(String value){return new StringBuilder().append(top).append(":").append(value).append(";\n").toString();}
    public static  String unicodeBidi(String value){return new StringBuilder().append(unicodeBidi).append(":").append(value).append(";\n").toString();}
    public static  String verticalAlign(String value){return new StringBuilder().append(verticalAlign).append(":").append(value).append(";\n").toString();}
    public static  String visibility(String value){return new StringBuilder().append(visibility).append(":").append(value).append(";\n").toString();}
    public static  String voiceFamily(String value){return new StringBuilder().append(voiceFamily).append(":").append(value).append(";\n").toString();}
    public static  String volume(String value){return new StringBuilder().append(volume).append(":").append(value).append(";\n").toString();}
    public static  String whiteSpace(String value){return new StringBuilder().append(whiteSpace).append(":").append(value).append(";\n").toString();}
    public static  String widows(String value){return new StringBuilder().append(widows).append(":").append(value).append(";\n").toString();}
    public static  String width(String value){return new StringBuilder().append(width).append(":").append(value).append(";\n").toString();}
    public static  String wordSpacing(String value){return new StringBuilder().append(wordSpacing).append(":").append(value).append(";\n").toString();}
    public static  String zIndex(String value){return new StringBuilder().append(zIndex).append(":").append(value).append(";\n").toString();}
    
    public static  String url(String value){return new StringBuilder().append("url(").append(JsfUtil.getServletContext().getContextPath()+value).append(");\n").toString();}

    
    
   /* ***************************************************************
    * 
    *  convenience methods for commonly used styles like display:none, visibility:hidden, etc.
    ***********************************************/
    
    public static String displayNone(){
        return display("none");
    }
    public static String displayBlock(){
        return display("block");
    }
    public static String hidden(){
        return visibility("hidden");
    }
    public static String visible(){
        return visibility("visible");
    }


    
    
    
    
    
    
    
    
    
    
    
    
    

//    public static final  String azimuth = Azimuth.azimuth;
    public static final  String backgroundAttachment = BackgroundAttachment.backgroundAttachment;
    public static final  String backgroundColor = BackgroundColor.backgroundColor;
    public static final  String backgroundImage = BackgroundImage.backgroundImage;
    public static final  String backgroundPosition = BackgroundPosition.backgroundPosition;
    public static final  String backgroundRepeat = BackgroundRepeat.backgroundRepeat;
    public static final  String background = Background.background;
    public static final  String borderCollapse = BorderCollapse.borderCollapse;
    public static final  String borderColor = BorderColor.borderColor;
    public static final  String borderSpacing = BorderSpacing.borderSpacing;
    public static final  String borderStyle = BorderStyle.borderStyle;
    public static final  String borderTop = BorderTop.borderTop;
    public static final  String borderRight = BorderRight.borderRight;
    public static final  String borderBottom = BorderBottom.borderBottom;
    public static final  String borderLeft = BorderLeft.borderLeft;
    public static final  String borderTopColor = BorderTopColor.borderTopColor;
    public static final  String borderRightColor = BorderRightColor.borderRightColor;
    public static final  String borderBottomColor = BorderBottomColor.borderBottomColor;
    public static final  String borderLeftColor = BorderLeftColor.borderLeftColor;
    public static final  String borderTopStyle = BorderTopStyle.borderTopStyle;
    public static final  String borderRightStyle = BorderRightStyle.borderRightStyle;
    public static final  String borderBottomStyle = BorderBottomStyle.borderBottomStyle;
    public static final  String borderLeftStyle = BorderLeftStyle.borderLeftStyle;
    public static final  String borderTopWidth = BorderTopWidth.borderTopWidth;
    public static final  String borderRightWidth = BorderRightWidth.borderRightWidth;
    public static final  String borderBottomWidth = BorderBottomWidth.borderBottomWidth;
    public static final  String borderLeftWidth = BorderLeftWidth.borderLeftWidth;
    public static final  String borderWidth = BorderWidth.borderWidth;
    public static final  String border = Border.border;
    public static final  String bottom = Bottom.bottom;
    public static final  String captionSide = CaptionSide.captionSide;
    public static final  String clear = Clear.clear;
    public static final  String clip = Clip.clip;
    public static final  String color = Color.color;
    public static final  String content = Content.content;
    public static final  String counterIncrement = CounterIncrement.counterIncrement;
    public static final  String counterReset = CounterReset.counterReset;
    public static final  String cueAfter = CueAfter.cueAfter;
    public static final  String cueBefore = CueBefore.cueBefore;
    public static final  String cue = Cue.cue;
    public static final  String cursor = Cursor.cursor;
    public static final  String direction = Direction.direction;
    public static final  String display = Display.display;
    public static final  String elevation = Elevation.elevation;
    public static final  String emptyCells = EmptyCells.emptyCells;
    public static final  String cssCssFloat = Float.cssCssFloat;
    public static final  String fontFamily = FontFamily.fontFamily;
    public static final  String fontSize = FontSize.fontSize;
    public static final  String fontStyle = FontStyle.fontStyle;
    public static final  String fontVariant = FontVariant.fontVariant;
    public static final  String fontWeight = FontWeight.fontWeight;
    public static final  String font = Font.font;
    public static final  String height = Height.height;
    public static final  String left = Left.left;
    public static final  String letterSpacing = LetterSpacing.letterSpacing;
    public static final  String lineHeight = LineHeight.lineHeight;
    public static final  String listStyleImage = ListStyleImage.listStyleImage;
    public static final  String listStylePosition = ListStylePosition.listStylePosition;
    public static final  String listStyleType = ListStyleType.listStyleType;
    public static final  String listStyle = ListStyle.listStyle;
    public static final  String marginRight = MarginRight.marginRight;
    public static final  String marginLeft = MarginLeft.marginLeft;
    public static final  String marginTop = MarginTop.marginTop;
    public static final  String marginBottom = MarginBottom.marginBottom;
    public static final  String margin = Margin.margin;
    public static final  String maxHeight = MaxHeight.maxHeight;
    public static final  String maxWidth = MaxWidth.maxWidth;
    public static final  String minHeight = MinHeight.minHeight;
    public static final  String minWidth = MinWidth.minWidth;
    public static final  String orphans = Orphans.orphans;
    public static final  String outlineColor = OutlineColor.outlineColor;
    public static final  String outlineStyle = OutlineStyle.outlineStyle;
    public static final  String outlineWidth = OutlineWidth.outlineWidth;
    public static final  String outline = Outline.outline;
    public static final  String overflow = Overflow.overflow;
    public static final  String paddingTop = PaddingTop.paddingTop;
    public static final  String paddingRight = PaddingRight.paddingRight;
    public static final  String paddingBottom = PaddingBottom.paddingBottom;
    public static final  String paddingLeft = PaddingLeft.paddingLeft;
    public static final  String padding = Padding.padding;
    public static final  String pageBreakAfter = PageBreakAfter.pageBreakAfter;
    public static final  String pageBreakBefore = PageBreakBefore.pageBreakBefore;
    public static final  String pageBreakInside = PageBreakInside.pageBreakInside;
    public static final  String pauseAfter = PauseAfter.pauseAfter;
    public static final  String pauseBefore = PauseBefore.pauseBefore;
    public static final  String pause = Pause.pause;
    public static final  String pitchRange = PitchRange.pitchRange;
    public static final  String pitch = Pitch.pitch;
    public static final  String playDuring = PlayDuring.playDuring;
    public static final  String position = Position.position;
    public static final  String quotes = Quotes.quotes;
    public static final  String richness = Richness.richness;
    public static final  String right = Right.right;
    public static final  String speakHeader = SpeakHeader.speakHeader;
    public static final  String speakNumeral = SpeakNumeral.speakNumeral;
    public static final  String speakPunctuation = SpeakPunctuation.speakPunctuation;
    public static final  String speak = Speak.speak;
    public static final  String speechRate = SpeechRate.speechRate;
    public static final  String stress = Stress.stress;
    public static final  String tableLayout = TableLayout.tableLayout;
    public static final  String textAlign = TextAlign.textAlign;
    public static final  String textDecoration = TextDecoration.textDecoration;
    public static final  String textIndent = TextIndent.textIndent;
    public static final  String textTransform = TextTransform.textTransform;
    public static final  String top = Top.top;
    public static final  String unicodeBidi = UnicodeBidi.unicodeBidi;
    public static final  String verticalAlign = VerticalAlign.verticalAlign;
    public static final  String visibility = Visibility.visibility;
    public static final  String voiceFamily = VoiceFamily.voiceFamily;
    public static final  String volume = Volume.volume;
    public static final  String whiteSpace = WhiteSpace.whiteSpace;
    public static final  String widows = Widows.widows;
    public static final  String width = Width.width;
    public static final  String wordSpacing = WordSpacing.wordSpacing;
    public static final  String zIndex = ZIndex.zIndex;
    
    
//    public static final  String azimuthValue = Azimuth.azimuthValue;
//    public static final  String backgroundAttachmentValue = BackgroundAttachment.backgroundAttachmentValue;
//    public static final  String backgroundColorValue = BackgroundColor.backgroundColorValue;
//    public static final  String backgroundImageValue = BackgroundImage.backgroundImageValue;
//    public static final  String backgroundPositionValue = BackgroundPosition.backgroundPositionValue;
//    public static final  String backgroundRepeatValue = BackgroundRepeat.backgroundRepeatValue;
//    public static final  String backgroundValue = Background.backgroundValue;
//    public static final  String borderCollapseValue = BorderCollapse.borderCollapseValue;
//    public static final  String borderColorValue = BorderColor.borderColorValue;
//    public static final  String borderSpacingValue = BorderSpacing.borderSpacingValue;
//    public static final  String borderStyleValue = BorderStyle.borderStyleValue;
//    public static final  String borderTopValue = BorderTop.borderTopValue;
//    public static final  String borderRightValue = BorderRight.borderRightValue;
//    public static final  String borderBottomValue = BorderBottom.borderBottomValue;
//    public static final  String borderLeftValue = BorderLeft.borderLeftValue;
//    public static final  String borderTopColorValue = BorderTopColor.borderTopColorValue;
//    public static final  String borderRightColorValue = BorderRightColor.borderRightColorValue;
//    public static final  String borderBottomColorValue = BorderBottomColor.borderBottomColorValue;
//    public static final  String borderLeftColorValue = BorderLeftColor.borderLeftColorValue;
//    public static final  String borderTopStyleValue = BorderTopStyle.borderTopStyleValue;
//    public static final  String borderRightStyleValue = BorderRightStyle.borderRightStyleValue;
//    public static final  String borderBottomStyleValue = BorderBottomStyle.borderBottomStyleValue;
//    public static final  String borderLeftStyleValue = BorderLeftStyle.borderLeftStyleValue;
//    public static final  String borderTopWidthValue = BorderTopWidth.borderTopWidthValue;
//    public static final  String borderRightWidthValue = BorderRightWidth.borderRightWidthValue;
//    public static final  String borderBottomWidthValue = BorderBottomWidth.borderBottomWidthValue;
//    public static final  String borderLeftWidthValue = BorderLeftWidth.borderLeftWidthValue;
//    public static final  String borderWidthValue = BorderWidth.borderWidthValue;
//    public static final  String borderValue = Border.borderValue;
//    public static final  String bottomValue = Bottom.bottomValue;
//    public static final  String captionSideValue = CaptionSide.captionSideValue;
//    public static final  String clearValue = Clear.clearValue;
//    public static final  String clipValue = Clip.clipValue;
//    public static final  String colorValue = Color.colorValue;
//    public static final  String contentValue = Content.contentValue;
//    public static final  String counterIncrementValue = CounterIncrement.counterIncrementValue;
//    public static final  String counterResetValue = CounterReset.counterResetValue;
//    public static final  String cueAfterValue = CueAfter.cueAfterValue;
//    public static final  String cueBeforeValue = CueBefore.cueBeforeValue;
//    public static final  String cueValue = Cue.cueValue;
//    public static final  String cursorValue = Cursor.cursorValue;
//    public static final  String directionValue = Direction.directionValue;
//    public static final  String displayValue = Display.displayValue;
//    public static final  String elevationValue = Elevation.elevationValue;
//    public static final  String emptyCellsValue = EmptyCells.emptyCellsValue;
//    public static final  String cssCssFloatValue = Float.cssCssFloatValue;
//    public static final  String fontFamilyValue = FontFamily.fontFamilyValue;
//    public static final  String fontSizeValue = FontSize.fontSizeValue;
//    public static final  String fontStyleValue = FontStyle.fontStyleValue;
//    public static final  String fontVariantValue = FontVariant.fontVariantValue;
//    public static final  String fontWeightValue = FontWeight.fontWeightValue;
//    public static final  String fontValue = Font.fontValue;
//    public static final  String heightValue = Height.heightValue;
//    public static final  String leftValue = Left.leftValue;
//    public static final  String letterSpacingValue = LetterSpacing.letterSpacingValue;
//    public static final  String lineHeightValue = LineHeight.lineHeightValue;
//    public static final  String listStyleImageValue = ListStyleImage.listStyleImageValue;
//    public static final  String listStylePositionValue = ListStylePosition.listStylePositionValue;
//    public static final  String listStyleTypeValue = ListStyleType.listStyleTypeValue;
//    public static final  String listStyleValue = ListStyle.listStyleValue;
//    public static final  String marginRightValue = MarginRight.marginRightValue;
//    public static final  String marginLeftValue = MarginLeft.marginLeftValue;
//    public static final  String marginTopValue = MarginTop.marginTopValue;
//    public static final  String marginBottomValue = MarginBottom.marginBottomValue;
//    public static final  String marginValue = Margin.marginValue;
//    public static final  String maxHeightValue = MaxHeight.maxHeightValue;
//    public static final  String maxWidthValue = MaxWidth.maxWidthValue;
//    public static final  String minHeightValue = MinHeight.minHeightValue;
//    public static final  String minWidthValue = MinWidth.minWidthValue;
//    public static final  String orphansValue = Orphans.orphansValue;
//    public static final  String outlineColorValue = OutlineColor.outlineColorValue;
//    public static final  String outlineStyleValue = OutlineStyle.outlineStyleValue;
//    public static final  String outlineWidthValue = OutlineWidth.outlineWidthValue;
//    public static final  String outlineValue = Outline.outlineValue;
//    public static final  String overflowValue = Overflow.overflowValue;
//    public static final  String paddingTopValue = PaddingTop.paddingTopValue;
//    public static final  String paddingRightValue = PaddingRight.paddingRightValue;
//    public static final  String paddingBottomValue = PaddingBottom.paddingBottomValue;
//    public static final  String paddingLeftValue = PaddingLeft.paddingLeftValue;
//    public static final  String paddingValue = Padding.paddingValue;
//    public static final  String pageBreakAfterValue = PageBreakAfter.pageBreakAfterValue;
//    public static final  String pageBreakBeforeValue = PageBreakBefore.pageBreakBeforeValue;
//    public static final  String pageBreakInsideValue = PageBreakInside.pageBreakInsideValue;
//    public static final  String pauseAfterValue = PauseAfter.pauseAfterValue;
//    public static final  String pauseBeforeValue = PauseBefore.pauseBeforeValue;
//    public static final  String pauseValue = Pause.pauseValue;
//    public static final  String pitchRangeValue = PitchRange.pitchRangeValue;
//    public static final  String pitchValue = Pitch.pitchValue;
//    public static final  String playDuringValue = PlayDuring.playDuringValue;
//    public static final  String positionValue = Position.positionValue;
//    public static final  String quotesValue = Quotes.quotesValue;
//    public static final  String richnessValue = Richness.richnessValue;
//    public static final  String rightValue = Right.rightValue;
//    public static final  String speakHeaderValue = SpeakHeader.speakHeaderValue;
//    public static final  String speakNumeralValue = SpeakNumeral.speakNumeralValue;
//    public static final  String speakPunctuationValue = SpeakPunctuation.speakPunctuationValue;
//    public static final  String speakValue = Speak.speakValue;
//    public static final  String speechRateValue = SpeechRate.speechRateValue;
//    public static final  String stressValue = Stress.stressValue;
//    public static final  String tableLayoutValue = TableLayout.tableLayoutValue;
//    public static final  String textAlignValue = TextAlign.textAlignValue;
//    public static final  String textDecorationValue = TextDecoration.textDecorationValue;
//    public static final  String textIndentValue = TextIndent.textIndentValue;
//    public static final  String textTransformValue = TextTransform.textTransformValue;
//    public static final  String topValue = Top.topValue;
//    public static final  String unicodeBidiValue = UnicodeBidi.unicodeBidiValue;
//    public static final  String verticalAlignValue = VerticalAlign.verticalAlignValue;
//    public static final  String visibilityValue = Visibility.visibilityValue;
//    public static final  String voiceFamilyValue = VoiceFamily.voiceFamilyValue;
//    public static final  String volumeValue = Volume.volumeValue;
//    public static final  String whiteSpaceValue = WhiteSpace.whiteSpaceValue;
//    public static final  String widowsValue = Widows.widowsValue;
//    public static final  String widthValue = Width.widthValue;
//    public static final  String wordSpacingValue = WordSpacing.wordSpacingValue;
//    public static final  String zIndexValue = ZIndex.zIndexValue;




public static class MediaGroups{
    public static final  String visual = "visual";
    public static final  String aural = "aural";
    public static final  String interactive = "interactive";
    public static final  String paged = "paged";
    public static final  String all = "all";
}
public static class BackgroundAttachment{
    public static final  String backgroundAttachment = "background-attachment";

    public static final  String scroll = "scroll";
    public static final  String fixed = "fixed";
    public static final  String inherit = "inherit";
    public static final  String initialValue = scroll;
    public static final  String backgroundAttachmentValue = initialValue;
    public static final  String appliesTo = " 		 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 		 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BackgroundColor{
    public static final  String backgroundColor = "background-color";
    public static final  String color = "color";
    public static final  String transparent = "transparent";
    public static final  String inherit = "inherit";
    public static final  String initialValue = transparent;
    public static final  String backgroundColorValue = initialValue;
    public static final  String appliesTo = " 		 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 		 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}

public static class BackgroundImage{
    public static final  String backgroundImage = "background-image";
    public static final  String uri = "uri";
    public static final  String none = "none";
    public static final  String inherit = "inherit";
    public static final  String initialValue = none;
    public static final  String backgroundImageValue = initialValue;
    public static final  String appliesTo = " 		 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 		 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}

public static class BackgroundPosition{
    public static final  String backgroundPosition = "background-position";
    public static final  String percentage = "percentage";
    public static final  String length = "length";
    public static final  String left = "left";
    public static final  String right = "right";
    public static final  String top = "top";
    public static final  String center = "center";
    public static final  String bottom = "bottom";
    public static final  String inherit = "inherit";
    public static final  String initialValue = " 	0%  0% 	";
    public static final  String backgroundPositionValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to the size of the box itself 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BackgroundRepeat{
    public static final  String backgroundRepeat = "background-repeat";
    public static final  String repeat = "repeat";
    public static final  String repeatX = "repeat-x";
    public static final  String repeatY = "repeat-y";
    public static final  String noRepeat = "no-repeat";
    public static final  String inherit = "inherit";
    public static final  String initialValue = repeat;
    public static final  String backgroundRepeatValue = initialValue;
    public static final  String appliesTo = " 		 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 		 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class Background{
    public static final  String background = "background";
    public static final  String backgroundColor = "background-color";
    public static final  String backgroundImage = "background-image";
    public static final  String backgroundRepeat = "background-repeat";
    public static final  String backgroundAttachment = "background-attachment";
    public static final  String backgroundPosition = "background-position";
    public static final  String inherit = "inherit";
    public static final  String initialValue = " 	transparent	";
    public static final  String backgroundValue = initialValue;
    public static final  String appliesTo = " 	allowed on \"background-position\" 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	see individual properties 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderCollapse{
    public static final  String borderCollapse = "border-collapse";
    public static final  String collapse = "collapse";
    public static final  String separate = "separate";
    public static final  String inherit = "inherit";
    public static final  String initialValue = separate;
    public static final  String borderCollapseValue = initialValue;
    public static final  String appliesTo = " 	\"table\" and \"inline-table\" elements 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 		 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderColor{
    public static final  String borderColor = "border-color";
    public static final  String color = "color";
    public static final  String transparent = "transparent";
    public static final  String inherit = "inherit";
    public static final  String initialValue = transparent;
    public static final  String borderColorValue = initialValue;
    public static final  String appliesTo = " 	see individual properties 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 		 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderSpacing{
    public static final  String borderSpacing = "border-spacing";
    public static final  String length = "length";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "0";
    public static final  String borderSpacingValue = initialValue;
    public static final  String appliesTo = " 	\"table\" and \"inline-table\" elements  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 		 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderStyle{
    public static final  String borderStyle = "border-style";
    public static final  String dotted = "dotted";
    public static final  String dashed = "dashed";
    public static final  String solid = "solid";
    public static final  String cssDouble = "double";
    public static final  String groove = "groove";
    public static final  String ridge = "ridge";
    public static final  String inset = "inset";
    public static final  String outset = "outset";
    public static final  String initialValue = " 	thin solid black	";
    public static final  String borderStyleValue = initialValue;
    public static final  String appliesTo = "	visual elements	";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 		 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderTop{
    public static final  String borderTop = "border-top";
    public static final  String inherit = "inherit";
    public static final  String initialValue = " 	thin solid black	";
    public static final  String borderTopValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderRight{
    public static final  String borderRight = "border-right";
    public static final  String inherit = "inherit";
    public static final  String initialValue = " 	thin solid black	";
    public static final  String borderRightValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderBottom{
    public static final  String borderBottom = "border-bottom";
    public static final  String inherit = "inherit";
    public static final  String initialValue = " 	thin solid black	";
    public static final  String borderBottomValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderLeft{
    public static final  String borderLeft = "border-left";
    public static final  String inherit = "inherit";
    public static final  String initialValue = " 	thin solid black	";
    public static final  String borderLeftValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderTopColor{
    public static final  String borderTopColor = "border-top-color";
    public static final  String color = "color";
    public static final  String transparent = "transparent";
    public static final  String inherit = "inherit";
    public static final  String initialValue = Border.color;
    public static final  String borderTopColorValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderRightColor{
    public static final  String borderRightColor = "border-right-color";
    public static final  String color = "color";
    public static final  String transparent = "transparent";
    public static final  String inherit = "inherit";
    public static final  String initialValue = Border.color;
    public static final  String borderRightColorValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderBottomColor{
    public static final  String borderBottomColor = "border-bottom-color";
    public static final  String initialValue = BorderColor.initialValue;
    public static final  String borderBottomColorValue = initialValue;
    public static final  String appliesTo = " 		 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 		 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderLeftColor{
    public static final  String borderLeftColor = "border-left-color";
    public static final  String color = "color";
    public static final  String transparent = "transparent";
    public static final  String inherit = "inherit";
    public static final  String initialValue = BorderColor.initialValue;
    public static final  String borderLeftColorValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderTopStyle{
    public static final  String borderTopStyle = "border-top-style";
    public static final  String inherit = "inherit";
    public static final  String initialValue = BorderStyle.initialValue;
    public static final  String borderTopStyleValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderRightStyle{
    public static final  String borderRightStyle = "border-right-style";
    public static final  String inherit = "inherit";
    public static final  String initialValue = Border.style;
    public static final  String borderRightStyleValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderBottomStyle{
    public static final  String borderBottomStyle = "border-bottom-style";
    public static final  String inherit = "inherit";
    public static final  String initialValue = Border.style;
    public static final  String borderBottomStyleValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderLeftStyle{
    public static final  String borderLeftStyle = "border-left-style";
    public static final  String inherit = "inherit";
    public static final  String initialValue = Border.style;
    public static final  String borderLeftStyleValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderTopWidth{
    public static final  String borderTopWidth = "border-top-width";
    public static final  String inherit = "inherit";
    public static final  String medium = "medium";
    public static final  String initialValue = medium;
    public static final  String borderTopWidthValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderRightWidth{
    public static final  String borderRightWidth = "border-right-width";
    public static final  String medium = "medium";
    public static final  String inherit = "inherit";
    public static final  String initialValue = medium;
    public static final  String borderRightWidthValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderBottomWidth{
    public static final  String borderBottomWidth = "border-bottom-width";
    public static final  String inherit = "inherit";
    public static final  String medium = "medium";
    public static final  String initialValue = medium;
    public static final  String borderBottomWidthValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderLeftWidth{
    public static final  String borderLeftWidth = "border-left-width";
    public static final  String inherit = "inherit";
    public static final  String medium = "medium";
    public static final  String initialValue = medium;
    public static final  String borderLeftWidthValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class BorderWidth{
    public static final  String borderWidth = "border-width";
    public static final  String inherit = "inherit";
    public static final  String thin = "thin";
    public static final  String thick = "thick";
    public static final  String medium = "medium";
    public static final  String initialValue = medium;
    public static final  String borderWidthValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class Border{
    public static final  String border = "border";
    public static final  String borderStyle = "border-style";
    public static final  String borderTopColor = "border-top-color";
    public static final  String borderBottom = BorderBottom.borderBottom;
    public static final  String borderTop = BorderTop.borderTop;
    public static final  String borderLeft = BorderLeft.borderLeft;
    public static final  String borderRight = BorderRight.borderRight;
    public static final  String borderTopValue = BorderTop.borderTopValue;
    public static final  String borderBottomValue = BorderBottom.borderBottomValue;
    public static final  String borderLeftValue = BorderLeft.borderLeftValue;
    public static final  String borderRightValue = BorderRight.borderRightValue;
    public static final  String inherit = "inherit";
    public static final  String medium = "medium";
    public static final  String color = BorderColor.initialValue;
    public static final  String style = BorderStyle.initialValue;
    public static final  String initialValue = medium;
    public static final  String borderValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class Bottom{
    public static final  String bottom = "bottom";
    public static final  String length = "length";
    public static final  String percentage = "percentage";
    public static final  String auto = "auto";
    public static final  String inherit = "inherit";
    public static final  String initialValue = auto;
    public static final  String bottomValue = initialValue;
    public static final  String appliesTo = " 	positioned elements 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to height of containing block 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class CaptionSide{
    public static final  String captionSide = "caption-side";
    public static final  String top = "top";
    public static final  String bottom = "bottom";
    public static final  String inherit = "inherit";
    public static final  String initialValue = top;
    public static final  String captionSideValue = initialValue;
    public static final  String appliesTo = " 	\"table-caption\" elements 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class Clear{
    public static final  String clear = "clear";
    public static final  String none = "none";
    public static final  String left = "left";
    public static final  String right = "right";
    public static final  String both = "both";
    public static final  String inherit = "inherit";
    public static final  String initialValue = none;
    public static final  String clearValue = initialValue;
    public static final  String appliesTo = " 	block-level elements 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class Clip{
    public static final  String clip = "clip";
    public static final  String shape = "shape";
    public static final  String auto = "auto";
    public static final  String inherit = "inherit";
    public static final  String initialValue = auto;
    public static final  String clipValue = initialValue;
    public static final  String appliesTo = " 	absolutely positioned elements 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}




public static class IndianRed{
    public static final  String hex = "#CD5C5C";
    public static final  String rgb = "205 92 92";
}

public static class LightCoral{
    public static final  String hex = "#F08080";
    public static final  String rgb = "240 128 128";
}

public static class Salmon{
    public static final  String hex = "#FA8072";
    public static final  String rgb = "250 128 114";
}

public static class DarkSalmon{
    public static final  String hex = "#E9967A";
    public static final  String rgb = "233 150 122";
}

public static class LightSalmon{
    public static final  String hex = "#FFA07A";
    public static final  String rgb = "255 160 122";
}

public static class Crimson{
    public static final  String hex = "#DC143C";
    public static final  String rgb = "220 20 60";
}

public static class Red{
    public static final  String hex = "#FF0000";
    public static final  String rgb = "255 0 0";
}

public static class FireBrick{
    public static final  String hex = "#B22222";
    public static final  String rgb = "178 34 34";
}

public static class DarkRed{
    public static final  String hex = "#8B0000";
    public static final  String rgb = "139 0 0";
}

public static class Pink{
    public static final  String hex = "#FFC0CB";
    public static final  String rgb = "255 192 203";
}

public static class LightPink{
    public static final  String hex = "#FFB6C1";
    public static final  String rgb = "255 182 193";
}

public static class HotPink{
    public static final  String hex = "#FF69B4";
    public static final  String rgb = "255 105 180";
}

public static class DeepPink{
    public static final  String hex = "#FF1493";
    public static final  String rgb = "255 20 147";
}

public static class MediumVioletRed{
    public static final  String hex = "#C71585";
    public static final  String rgb = "199 21 133";
}

public static class PaleVioletRed{
    public static final  String hex = "#DB7093";
    public static final  String rgb = "219 112 147";
}

public static class Coral{
    public static final  String hex = "#FF7F50";
    public static final  String rgb = "255 127 80";
}

public static class Tomato{
    public static final  String hex = "#FF6347";
    public static final  String rgb = "255 99 71";
}

public static class OrangeRed{
    public static final  String hex = "#FF4500";
    public static final  String rgb = "255 69 0";
}

public static class DarkOrange{
    public static final  String hex = "#FF8C00";
    public static final  String rgb = "255 140 0";
}

public static class Orange{
    public static final  String hex = "#FFA500";
    public static final  String rgb = "255 165 0";
}

public static class Gold{
    public static final  String hex = "#FFD700";
    public static final  String rgb = "255 215 0";
}

public static class Yellow{
    public static final  String hex = "#FFFF00";
    public static final  String rgb = "255 255 0";
}

public static class LightYellow{
    public static final  String hex = "#FFFFE0";
    public static final  String rgb = "255 255 224";
}

public static class LemonChiffon{
    public static final  String hex = "#FFFACD";
    public static final  String rgb = "255 250 205";
}

public static class LightGoldenrodYellow{
    public static final  String hex = "#FAFAD2";
    public static final  String rgb = "250 250 210";
}

public static class PapayaWhip{
    public static final  String hex = "#FFEFD5";
    public static final  String rgb = "255 239 213";
}

public static class Moccasin{
    public static final  String hex = "#FFE4B5";
    public static final  String rgb = "255 228 181";
}

public static class PeachPuff{
    public static final  String hex = "#FFDAB9";
    public static final  String rgb = "255 218 185";
}

public static class PaleGoldenrod{
    public static final  String hex = "#EEE8AA";
    public static final  String rgb = "238 232 170";
}

public static class Khaki{
    public static final  String hex = "#F0E68C";
    public static final  String rgb = "240 230 140";
}

public static class DarkKhaki{
    public static final  String hex = "#BDB76B";
    public static final  String rgb = "189 183 107";
}

public static class Lavender{
    public static final  String hex = "#E6E6FA";
    public static final  String rgb = "230 230 250";
}

public static class Thistle{
    public static final  String hex = "#D8BFD8";
    public static final  String rgb = "216 191 216";
}

public static class Plum{
    public static final  String hex = "#DDA0DD";
    public static final  String rgb = "221 160 221";
}

public static class Violet{
    public static final  String hex = "#EE82EE";
    public static final  String rgb = "238 130 238";
}

public static class Orchid{
    public static final  String hex = "#DA70D6";
    public static final  String rgb = "218 112 214";
}

public static class Fuchsia{
    public static final  String hex = "#FF00FF";
    public static final  String rgb = "255 0 255";
}

public static class Magenta{
    public static final  String hex = "#FF00FF";
    public static final  String rgb = "255 0 255";
}

public static class MediumOrchid{
    public static final  String hex = "#BA55D3";
    public static final  String rgb = "186 85 211";
}

public static class MediumPurple{
    public static final  String hex = "#9370DB";
    public static final  String rgb = "147 112 219";
}

public static class BlueViolet{
    public static final  String hex = "#8A2BE2";
    public static final  String rgb = "138 43 226";
}

public static class DarkViolet{
    public static final  String hex = "#9400D3";
    public static final  String rgb = "148 0 211";
}

public static class DarkOrchid{
    public static final  String hex = "#9932CC";
    public static final  String rgb = "153 50 204";
}

public static class DarkMagenta{
    public static final  String hex = "#8B008B";
    public static final  String rgb = "139 0 139";
}

public static class Purple{
    public static final  String hex = "#800080";
    public static final  String rgb = "128 0 128";
}

public static class Indigo{
    public static final  String hex = "#4B0082";
    public static final  String rgb = "75 0 130";
}

public static class SlateBlue{
    public static final  String hex = "#6A5ACD";
    public static final  String rgb = "106 90 205";
}

public static class DarkSlateBlue{
    public static final  String hex = "#483D8B";
    public static final  String rgb = "72 61 139";
}

public static class MediumSlateBlue{
    public static final  String hex = "#7B68EE";
    public static final  String rgb = "123 104 238";
}

public static class GreenYellow{
    public static final  String hex = "#ADFF2F";
    public static final  String rgb = "173 255 47";
}

public static class Chartreuse{
    public static final  String hex = "#7FFF00";
    public static final  String rgb = "127 255 0";
}

public static class LawnGreen{
    public static final  String hex = "#7CFC00";
    public static final  String rgb = "124 252 0";
}

public static class Lime{
    public static final  String hex = "#00FF00";
    public static final  String rgb = "0 255 0";
}

public static class LimeGreen{
    public static final  String hex = "#32CD32";
    public static final  String rgb = "50 205 50";
}

public static class PaleGreen{
    public static final  String hex = "#98FB98";
    public static final  String rgb = "152 251 152";
}

public static class LightGreen{
    public static final  String hex = "#90EE90";
    public static final  String rgb = "144 238 144";
}

public static class MediumSpringGreen{
    public static final  String hex = "#00FA9A";
    public static final  String rgb = "0 250 154";
}

public static class SpringGreen{
    public static final  String hex = "#00FF7F";
    public static final  String rgb = "0 255 127";
}

public static class MediumSeaGreen{
    public static final  String hex = "#3CB371";
    public static final  String rgb = "60 179 113";
}

public static class SeaGreen{
    public static final  String hex = "#2E8B57";
    public static final  String rgb = "46 139 87";
}

public static class ForestGreen{
    public static final  String hex = "#228B22";
    public static final  String rgb = "34 139 34";
}

public static class Green{
    public static final  String hex = "#008000";
    public static final  String rgb = "0 128 0";
}

public static class DarkGreen{
    public static final  String hex = "#006400";
    public static final  String rgb = "0 100 0";
}

public static class YellowGreen{
    public static final  String hex = "#9ACD32";
    public static final  String rgb = "154 205 50";
}

public static class OliveDrab{
    public static final  String hex = "#6B8E23";
    public static final  String rgb = "107 142 35";
}

public static class Olive{
    public static final  String hex = "#808000";
    public static final  String rgb = "128 128 0";
}

public static class DarkOliveGreen{
    public static final  String hex = "#556B2F";
    public static final  String rgb = "85 107 47";
}

public static class MediumAquamarine{
    public static final  String hex = "#66CDAA";
    public static final  String rgb = "102 205 170";
}

public static class DarkSeaGreen{
    public static final  String hex = "#8FBC8F";
    public static final  String rgb = "143 188 143";
}

public static class LightSeaGreen{
    public static final  String hex = "#20B2AA";
    public static final  String rgb = "32 178 170";
}

public static class DarkCyan{
    public static final  String hex = "#008B8B";
    public static final  String rgb = "0 139 139";
}

public static class Teal{
    public static final  String hex = "#008080";
    public static final  String rgb = "0 128 128";
}

public static class Aqua{
    public static final  String hex = "#00FFFF";
    public static final  String rgb = "0 255 255";
}

public static class Cyan{
    public static final  String hex = "#00FFFF";
    public static final  String rgb = "0 255 255";
}

public static class LightCyan{
    public static final  String hex = "#E0FFFF";
    public static final  String rgb = "224 255 255";
}

public static class PaleTurquoise{
    public static final  String hex = "#AFEEEE";
    public static final  String rgb = "175 238 238";
}

public static class Aquamarine{
    public static final  String hex = "#7FFFD4";
    public static final  String rgb = "127 255 212";
}

public static class Turquoise{
    public static final  String hex = "#40E0D0";
    public static final  String rgb = "64 224 208";
}

public static class MediumTurquoise{
    public static final  String hex = "#48D1CC";
    public static final  String rgb = "72 209 204";
}

public static class DarkTurquoise{
    public static final  String hex = "#00CED1";
    public static final  String rgb = "0 206 209";
}

public static class CadetBlue{
    public static final  String hex = "#5F9EA0";
    public static final  String rgb = "95 158 160";
}

public static class SteelBlue{
    public static final  String hex = "#4682B4";
    public static final  String rgb = "70 130 180";
}

public static class LightSteelBlue{
    public static final  String hex = "#B0C4DE";
    public static final  String rgb = "176 196 222";
}

public static class PurwaBlue{
    public static final  String hex = "#9BE1FF";
    public static final  String rgb = "155 225 255";
}

public static class PowderBlue{
    public static final  String hex = "#B0E0E6";
    public static final  String rgb = "176 224 230";
}

public static class LightBlue{
    public static final  String hex = "#ADD8E6";
    public static final  String rgb = "173 216 230";
}

public static class SkyBlue{
    public static final  String hex = "#87CEEB";
    public static final  String rgb = "135 206 235";
}

public static class LightSkyBlue{
    public static final  String hex = "#87CEFA";
    public static final  String rgb = "135 206 250";
}

public static class DeepSkyBlue{
    public static final  String hex = "#00BFFF";
    public static final  String rgb = "0 191 255";
}

public static class DodgerBlue{
    public static final  String hex = "#1E90FF";
    public static final  String rgb = "30 144 255";
}

public static class CornflowerBlue{
    public static final  String hex = "#6495ED";
    public static final  String rgb = "100 149 237";
}

public static class RoyalBlue{
    public static final  String hex = "#4169E1";
    public static final  String rgb = "65 105 225";
}

public static class Blue{
    public static final  String hex = "#0000FF";
    public static final  String rgb = "0 0 255";
}

public static class MediumBlue{
    public static final  String hex = "#0000CD";
    public static final  String rgb = "0 0 205";
}

public static class DarkBlue{
    public static final  String hex = "#00008B";
    public static final  String rgb = "0 0 139";
}

public static class Navy{
    public static final  String hex = "#000080";
    public static final  String rgb = "0 0 128";
}

public static class MidnightBlue{
    public static final  String hex = "#191970";
    public static final  String rgb = "25 25 112";
}

public static class Cornsilk{
    public static final  String hex = "#FFF8DC";
    public static final  String rgb = "255 248 220";
}

public static class BlanchedAlmond{
    public static final  String hex = "#FFEBCD";
    public static final  String rgb = "255 235 205";
}

public static class Bisque{
    public static final  String hex = "#FFE4C4";
    public static final  String rgb = "255 228 196";
}

public static class NavajoWhite{
    public static final  String hex = "#FFDEAD";
    public static final  String rgb = "255 222 173";
}

public static class Wheat{
    public static final  String hex = "#F5DEB3";
    public static final  String rgb = "245 222 179";
}

public static class BurlyWood{
    public static final  String hex = "#DEB887";
    public static final  String rgb = "222 184 135";
}

public static class Tan{
    public static final  String hex = "#D2B48C";
    public static final  String rgb = "210 180 140";
}

public static class RosyBrown{
    public static final  String hex = "#BC8F8F";
    public static final  String rgb = "188 143 143";
}

public static class SandyBrown{
    public static final  String hex = "#F4A460";
    public static final  String rgb = "244 164 96";
}

public static class Goldenrod{
    public static final  String hex = "#DAA520";
    public static final  String rgb = "218 165 32";
}

public static class DarkGoldenrod{
    public static final  String hex = "#B8860B";
    public static final  String rgb = "184 134 11";
}

public static class Peru{
    public static final  String hex = "#CD853F";
    public static final  String rgb = "205 133 63";
}

public static class Chocolate{
    public static final  String hex = "#D2691E";
    public static final  String rgb = "210 105 30";
}

public static class SaddleBrown{
    public static final  String hex = "#8B4513";
    public static final  String rgb = "139 69 19";
}

public static class Sienna{
    public static final  String hex = "#A0522D";
    public static final  String rgb = "160 82 45";
}

public static class Brown{
    public static final  String hex = "#A52A2A";
    public static final  String rgb = "165 42 42";
}

public static class Maroon{
    public static final  String hex = "#800000";
    public static final  String rgb = "128 0 0";
}

public static class White{
    public static final  String hex = "#FFFFFF";
    public static final  String rgb = "255 255 255";
}

public static class Snow{
    public static final  String hex = "#FFFAFA";
    public static final  String rgb = "255 250 250";
}

public static class Honeydew{
    public static final  String hex = "#F0FFF0";
    public static final  String rgb = "240 255 240";
}

public static class MintCream{
    public static final  String hex = "#F5FFFA";
    public static final  String rgb = "245 255 250";
}

public static class Azure{
    public static final  String hex = "#F0FFFF";
    public static final  String rgb = "240 255 255";
}

public static class AliceBlue{
    public static final  String hex = "#F0F8FF";
    public static final  String rgb = "240 248 255";
}

public static class GhostWhite{
    public static final  String hex = "#F8F8FF";
    public static final  String rgb = "248 248 255";
}

public static class WhiteSmoke{
    public static final  String hex = "#F5F5F5";
    public static final  String rgb = "245 245 245";
}

public static class Seashell{
    public static final  String hex = "#FFF5EE";
    public static final  String rgb = "255 245 238";
}

public static class Beige{
    public static final  String hex = "#F5F5DC";
    public static final  String rgb = "245 245 220";
}

public static class OldLace{
    public static final  String hex = "#FDF5E6";
    public static final  String rgb = "253 245 230";
}

public static class FloralWhite{
    public static final  String hex = "#FFFAF0";
    public static final  String rgb = "255 250 240";
}

public static class Ivory{
    public static final  String hex = "#FFFFF0";
    public static final  String rgb = "255 255 240";
}

public static class AntiqueWhite{
    public static final  String hex = "#FAEBD7";
    public static final  String rgb = "250 235 215";
}

public static class Linen{
    public static final  String hex = "#FAF0E6";
    public static final  String rgb = "250 240 230";
}

public static class LavenderBlush{
    public static final  String hex = "#FFF0F5";
    public static final  String rgb = "255 240 245";
}

public static class MistyRose{
    public static final  String hex = "#FFE4E1";
    public static final  String rgb = "255 228 225";
}

public static class Gainsboro{
    public static final  String hex = "#DCDCDC";
    public static final  String rgb = "220 220 220";
}

public static class LightGray{
    public static final  String hex = "#D3D3D3";
    public static final  String rgb = "211 211 211";
}

public static class Silver{
    public static final  String hex = "#C0C0C0";
    public static final  String rgb = "192 192 192";
}

public static class DarkGray{
    public static final  String hex = "#A9A9A9";
    public static final  String rgb = "169 169 169";
}

public static class Gray{
    public static final  String hex = "#808080";
    public static final  String rgb = "128 128 128";
}

public static class DimGray{
    public static final  String hex = "#696969";
    public static final  String rgb = "105 105 105";
}

public static class LightSlateGray{
    public static final  String hex = "#778899";
    public static final  String rgb = "119 136 153";
}

public static class SlateGray{
    public static final  String hex = "#708090";
    public static final  String rgb = "112 128 144";
}

public static class DarkSlateGray{
    public static final  String hex = "#2F4F4F";
    public static final  String rgb = "47 79 79";
}

public static class Black{
    public static final  String hex = "#000000";
    public static final  String rgb = "0 0 0";
}




public static class Color{
    public static final  String color = "color";
    public static final  String inherit = "inherit";
    public static final  String initialValue = " 	transparent	";
    public static final  String colorValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
    public static final  String indianRed = "#CD5C5C";
    public static final  String lightCoral = "#F08080";
    public static final  String salmon = "#FA8072";
    public static final  String darkSalmon = "#E9967A";
    public static final  String lightSalmon = "#FFA07A";
    public static final  String crimson = "#DC143C";
    public static final  String red = "#FF0000";
    public static final  String fireBrick = "#B22222";
    public static final  String darkRed = "#8B0000";
    public static final  String pink = "#FFC0CB";
    public static final  String lightPink = "#FFB6C1";
    public static final  String hotPink = "#FF69B4";
    public static final  String deepPink = "#FF1493";
    public static final  String mediumVioletRed = "#C71585";
    public static final  String paleVioletRed = "#DB7093";
    public static final  String coral = "#FF7F50";
    public static final  String tomato = "#FF6347";
    public static final  String orangeRed = "#FF4500";
    public static final  String darkOrange = "#FF8C00";
    public static final  String orange = "#FFA500";
    public static final  String gold = "#FFD700";
    public static final  String yellow = "#FFFF00";
    public static final  String lightYellow = "#FFFFE0";
    public static final  String lemonChiffon = "#FFFACD";
    public static final  String lightGoldenrodYellow = "#FAFAD2";
    public static final  String papayaWhip = "#FFEFD5";
    public static final  String moccasin = "#FFE4B5";
    public static final  String peachPuff = "#FFDAB9";
    public static final  String paleGoldenrod = "#EEE8AA";
    public static final  String khaki = "#F0E68C";
    public static final  String darkKhaki = "#BDB76B";
    public static final  String lavender = "#E6E6FA";
    public static final  String thistle = "#D8BFD8";
    public static final  String plum = "#DDA0DD";
    public static final  String violet = "#EE82EE";
    public static final  String orchid = "#DA70D6";
    public static final  String fuchsia = "#FF00FF";
    public static final  String magenta = "#FF00FF";
    public static final  String mediumOrchid = "#BA55D3";
    public static final  String mediumPurple = "#9370DB";
    public static final  String blueViolet = "#8A2BE2";
    public static final  String darkViolet = "#9400D3";
    public static final  String darkOrchid = "#9932CC";
    public static final  String darkMagenta = "#8B008B";
    public static final  String purple = "#800080";
    public static final  String indigo = "#4B0082";
    public static final  String slateBlue = "#6A5ACD";
    public static final  String darkSlateBlue = "#483D8B";
    public static final  String mediumSlateBlue = "#7B68EE";
    public static final  String greenYellow = "#ADFF2F";
    public static final  String chartreuse = "#7FFF00";
    public static final  String lawnGreen = "#7CFC00";
    public static final  String lime = "#00FF00";
    public static final  String limeGreen = "#32CD32";
    public static final  String paleGreen = "#98FB98";
    public static final  String lightGreen = "#90EE90";
    public static final  String mediumSpringGreen = "#00FA9A";
    public static final  String springGreen = "#00FF7F";
    public static final  String mediumSeaGreen = "#3CB371";
    public static final  String seaGreen = "#2E8B57";
    public static final  String forestGreen = "#228B22";
    public static final  String green = "#008000";
    public static final  String darkGreen = "#006400";
    public static final  String yellowGreen = "#9ACD32";
    public static final  String oliveDrab = "#6B8E23";
    public static final  String olive = "#808000";
    public static final  String darkOliveGreen = "#556B2F";
    public static final  String mediumAquamarine = "#66CDAA";
    public static final  String darkSeaGreen = "#8FBC8F";
    public static final  String lightSeaGreen = "#20B2AA";
    public static final  String darkCyan = "#008B8B";
    public static final  String teal = "#008080";
    public static final  String aqua = "#00FFFF";
    public static final  String cyan = "#00FFFF";
    public static final  String lightCyan = "#E0FFFF";
    public static final  String paleTurquoise = "#AFEEEE";
    public static final  String aquamarine = "#7FFFD4";
    public static final  String turquoise = "#40E0D0";
    public static final  String mediumTurquoise = "#48D1CC";
    public static final  String darkTurquoise = "#00CED1";
    public static final  String cadetBlue = "#5F9EA0";
    public static final  String steelBlue = "#4682B4";
    public static final  String lightSteelBlue = "#B0C4DE";
    public static final  String purwaBlue = "#9BE1FF";
    public static final  String powderBlue = "#B0E0E6";
    public static final  String lightBlue = "#ADD8E6";
    public static final  String skyBlue = "#87CEEB";
    public static final  String lightSkyBlue = "#87CEFA";
    public static final  String deepSkyBlue = "#00BFFF";
    public static final  String dodgerBlue = "#1E90FF";
    public static final  String cornflowerBlue = "#6495ED";
    public static final  String royalBlue = "#4169E1";
    public static final  String blue = "#0000FF";
    public static final  String mediumBlue = "#0000CD";
    public static final  String darkBlue = "#00008B";
    public static final  String navy = "#000080";
    public static final  String midnightBlue = "#191970";
    public static final  String cornsilk = "#FFF8DC";
    public static final  String blanchedAlmond = "#FFEBCD";
    public static final  String bisque = "#FFE4C4";
    public static final  String navajoWhite = "#FFDEAD";
    public static final  String wheat = "#F5DEB3";
    public static final  String burlyWood = "#DEB887";
    public static final  String tan = "#D2B48C";
    public static final  String rosyBrown = "#BC8F8F";
    public static final  String sandyBrown = "#F4A460";
    public static final  String goldenrod = "#DAA520";
    public static final  String darkGoldenrod = "#B8860B";
    public static final  String peru = "#CD853F";
    public static final  String chocolate = "#D2691E";
    public static final  String saddleBrown = "#8B4513";
    public static final  String sienna = "#A0522D";
    public static final  String brown = "#A52A2A";
    public static final  String maroon = "#800000";
    public static final  String white = "#FFFFFF";
    public static final  String snow = "#FFFAFA";
    public static final  String honeydew = "#F0FFF0";
    public static final  String mintCream = "#F5FFFA";
    public static final  String azure = "#F0FFFF";
    public static final  String aliceBlue = "#F0F8FF";
    public static final  String ghostWhite = "#F8F8FF";
    public static final  String whiteSmoke = "#F5F5F5";
    public static final  String seashell = "#FFF5EE";
    public static final  String beige = "#F5F5DC";
    public static final  String oldLace = "#FDF5E6";
    public static final  String floralWhite = "#FFFAF0";
    public static final  String ivory = "#FFFFF0";
    public static final  String antiqueWhite = "#FAEBD7";
    public static final  String linen = "#FAF0E6";
    public static final  String lavenderBlush = "#FFF0F5";
    public static final  String mistyRose = "#FFE4E1";
    public static final  String gainsboro = "#DCDCDC";
    public static final  String lightGray = "#D3D3D3";
    public static final  String silver = "#C0C0C0";
    public static final  String darkGray = "#A9A9A9";
    public static final  String gray = "#808080";
    public static final  String dimGray = "#696969";
    public static final  String lightSlateGray = "#778899";
    public static final  String slateGray = "#708090";
    public static final  String darkSlateGray = "#2F4F4F";
    public static final  String black = "#000000";
}

public static class Content{
    public static final  String content = "content";
    public static final  String normal = "normal";
    public static final  String none = "none";
    public static final  String string = "string";
    public static final  String uri = "uri";
    public static final  String counter = "counter";
    public static final  String openQuote = "open-quote";
    public static final  String closeQuote = "close-quote";
    public static final  String noOpenQuote = "no-open-quote";
    public static final  String noCloseQuote = "no-close-quote";
    public static final  String inherit = "inherit";
    public static final  String initialValue = normal;
    public static final  String contentValue = initialValue;
    public static final  String appliesTo = " 	:before and :after pseudo-elements 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.all});
}
public static class CounterIncrement{
    public static final  String counterIncrement = "counter-increment";
    public static final  String identifier = "identifier";
    public static final  String integer = "integer";
    public static final  String none = "none";
    public static final  String inherit = "inherit";
    public static final  String initialValue = none;
    public static final  String counterIncrementValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.all});
}
public static class CounterReset{
    public static final  String counterReset = "counter-reset";
    public static final  String identifier = "identifier";
    public static final  String integer = "integer";
    public static final  String none = "none";
    public static final  String inherit = "inherit";
    public static final  String initialValue = none;
    public static final  String counterResetValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.all});
}
public static class CueAfter{
    public static final  String cueAfter = "cue-after";
    public static final  String uri = "uri";
    public static final  String none = "none";
    public static final  String inherit = "inherit";
    public static final  String initialValue = none;
    public static final  String cueAfterValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.aural});
}
public static class CueBefore{
    public static final  String cueBefore = "cue-before";
    public static final  String uri = "uri";
    public static final  String none = "none";
    public static final  String inherit = "inherit";
    public static final  String initialValue = none;
    public static final  String cueBeforeValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.aural});
}
public static class Cue{
    public static final  String cue = "cue";
    public static final  String cueBefore = "cue-before";
    public static final  String cueAfter = "cue-after";
    public static final  String inherit = "inherit";
    public static final  String initialValue = " 	see individual properties 	";
    public static final  String cueValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.aural});
}
public static class Cursor{
    public static final  String cursor = "cursor";
    public static final  String uri = "uri";
    public static final  String auto = "auto";
    public static final  String crosshair = "crosshair";
    public static final  String cssStringault = "Stringault";
    public static final  String pointer = "pointer";
    public static final  String move = "move";
    public static final  String eResize = "e-resize";
    public static final  String neResize = "ne-resize";
    public static final  String nwResize = "nw-resize";
    public static final  String nResize = "n-resize";
    public static final  String seResize = "se-resize";
    public static final  String swResize = "sw-resize";
    public static final  String sResize = "s-resize";
    public static final  String wResize = "w-resize";
    public static final  String text = "text";
    public static final  String wait = "wait";
    public static final  String help = "help";
    public static final  String progress = "progress";
    public static final  String inherit = "inherit";
    public static final  String initialValue = auto;
    public static final  String cursorValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual, MediaGroups.interactive});
}
public static class Direction{
    public static final  String direction = "direction";
    public static final  String ltr = "ltr";
    public static final  String rtl = "rtl";
    public static final  String inherit = "inherit";
    public static final  String initialValue = ltr;
    public static final  String directionValue = initialValue;
    public static final  String appliesTo = " 	all elements, but see prose 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class Display{
    public static final  String display = "display";
    public static final  String inline = "inline";
    public static final  String block = "block";
    public static final  String listItem = "list-item";
    public static final  String runIn = "run-in";
    public static final  String inlineBlock = "inline-block";
    public static final  String table = "table";
    public static final  String inlineTable = "inline-table";
    public static final  String tableRowGroup = "table-row-group";
    public static final  String tableHeaderGroup = "table-header-group";
    public static final  String tableFooterGroup = "table-footer-group";
    public static final  String tableRow = "table-row";
    public static final  String tableColumnGroup = "table-column-group";
    public static final  String tableColumn = "table-column";
    public static final  String tableCell = "table-cell";
    public static final  String tableCaption = "table-caption";
    public static final  String none = "none";
    public static final  String inherit = "inherit";
    public static final  String initialValue = inline;
    public static final  String displayValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.all});
}
public static class Elevation{
    public static final  String elevation = "elevation";
    public static final  String level = "level";
    public static final  String initialValue = level;
    public static final  String elevationValue = initialValue;
    public static final  String appliesTo = " 		 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 		 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.aural});
}

public static class EmptyCells{
    public static final  String emptyCells = "empty-cells";
    public static final  String show = "show";
    public static final  String hide = "hide";
    public static final  String inherit = "inherit";
    public static final  String initialValue = show;
    public static final  String emptyCellsValue = initialValue;
    public static final  String appliesTo = " 	\"table-cell\" elements 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class Float{
    public static final  String cssCssFloat = "float";
    public static final  String left = "left";
    public static final  String right = "right";
    public static final  String none = "none";
    public static final  String inherit = "inherit";
    public static final  String initialValue = none;
    public static final  String cssCssFloatValue = initialValue;
    public static final  String appliesTo = " 	all, but see 9.7 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class FontFamily{
    public static final  String fontFamily = "font-family";
    public static final  String familyName = "family-name";
    public static final  String genericFamily = "generic-family";
    public static final  String inherit = "inherit";
    public static final  String auto = "auto";
    public static final  String initialValue = auto;
    public static final  String fontFamilyValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class FontSize{
    public static final  String fontSize = "font-size";
    public static final  String absoluteSize = "absolute-size";
    public static final  String relativeSize = "relative-size";
    public static final  String length = "length";
    public static final  String percentage = "percentage";
    public static final  String inherit = "inherit";
    public static final  String medium = "medium";
    public static final  String initialValue = medium;
    public static final  String fontSizeValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to parent element\"s font size 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class FontStyle{
    public static final  String fontStyle = "font-style";
    public static final  String normal = "normal";
    public static final  String italic = "italic";
    public static final  String oblique = "oblique";
    public static final  String inherit = "inherit";
    public static final  String initialValue = normal;
    public static final  String fontStyleValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class FontVariant{
    public static final  String fontVariant = "font-variant";
    public static final  String normal = "normal";
    public static final  String smallCaps = "small-caps";
    public static final  String inherit = "inherit";
    public static final  String initialValue = normal;
    public static final  String fontVariantValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class FontWeight{
    public static final  String fontWeight = "font-weight";
    public static final  String normal = "normal";
    public static final  String bold = "bold";
    public static final  String bolder = "bolder";
    public static final  String lighter = "lighter";
    public static final  String css100 = "100";
    public static final  String css200 = "200";
    public static final  String css300 = "300";
    public static final  String css400 = "400";
    public static final  String css500 = "500";
    public static final  String css600 = "600";
    public static final  String css700 = "700";
    public static final  String css800 = "800";
    public static final  String css900 = "900";
    public static final  String inherit = "inherit";
    public static final  String initialValue = normal;
    public static final  String fontWeightValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class Font{
    public static final  String font = "font";
    public static final  String fontStyle = "font-style";
    public static final  String fontVariant = "font-variant";
    public static final  String fontWeight = "font-weight";
    public static final  String fontSize = "font-size";
    public static final  String lineHeight = "line-height";
    public static final  String fontFamily = "font-family";
    public static final  String caption = "caption";
    public static final  String icon = "icon";
    public static final  String menu = "menu";
    public static final  String messageBox = "message-box";
    public static final  String smallCaption = "small-caption";
    public static final  String statusBar = "status-bar";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "10px";
    public static final  String fontValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	see individual properties 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class Height{
    public static final  String height = "height";
    public static final  String length = "length";
    public static final  String percentage = "percentage";
    public static final  String auto = "auto";
    public static final  String inherit = "inherit";
    public static final  String initialValue = auto;
    public static final  String heightValue = initialValue;
    public static final  String appliesTo = " 	all elements but non-replaced inline elements, table columns, and column groups 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	see prose 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class Left{
    public static final  String left = "left";
    public static final  String length = "length";
    public static final  String percentage = "percentage";
    public static final  String auto = "auto";
    public static final  String inherit = "inherit";
    public static final  String initialValue = auto;
    public static final  String leftValue = initialValue;
    public static final  String appliesTo = " 	positioned elements 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to width of containing block 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class LetterSpacing{
    public static final  String letterSpacing = "letter-spacing";
    public static final  String normal = "normal";
    public static final  String length = "length";
    public static final  String inherit = "inherit";
    public static final  String initialValue = normal;
    public static final  String letterSpacingValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class LineHeight{
    public static final  String lineHeight = "line-height";

    public static final  String normal = "normal";
    public static final  String initialValue = "	  	";
    public static final  String lineHeightValue = initialValue;
    public static final  String appliesTo = " 	yes 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	refer to the font size of the element itself 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 		 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class ListStyleImage{
    public static final  String listStyleImage = "list-style-image";
    public static final  String none = "none";
    public static final  String initialValue = " 		";
    public static final  String listStyleImageValue = initialValue;
    public static final  String appliesTo = " 	yes 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	  	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 		 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class ListStylePosition{
    public static final  String listStylePosition = "list-style-position";
    public static final  String inside = "inside";
    public static final  String outside = "outside";
    public static final  String initialValue = outside;
    public static final  String listStylePositionValue = initialValue;
    public static final  String appliesTo = " 	elements with \"display: list-item\" 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class ListStyleType{
    public static final  String listStyleType = "list-style-type";
    public static final  String disc = "disc";
    public static final  String decimal = "decimal";
    public static final  String decimalLeadingZero = "decimal-leading-zero";
    public static final  String lowerRoman = "lower-roman";
    public static final  String upperRoman = "upper-roman";
    public static final  String lowerGreek = "lower-greek";
    public static final  String lowerLatin = "lower-latin";
    public static final  String upperLatin = "upper-latin";
    public static final  String armenian = "armenian";
    public static final  String georgian = "georgian";
    public static final  String lowerAlpha = "lower-alpha";
    public static final  String upperAlpha = "upper-alpha";
    public static final  String none = "none";
    public static final  String inherit = "inherit";
    public static final  String square = "square";
    public static final  String circle = "circle";
    public static final  String initialValue = disc;
    public static final  String listStyleTypeValue = initialValue;
    public static final  String appliesTo = " 	elements with \"display: list-item\" 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class ListStyle{
    public static final  String listStyle = "list-style";
    public static final  String listStyleType = "list-style-type";
    public static final  String listStylePosition = "list-style-position";
    public static final  String listStyleImage = "list-style-image";
    public static final  String inherit = "inherit";
    public static final  String none = "none";
    public static final  String initialValue = none;
    public static final  String listStyleValue = initialValue;
    public static final  String appliesTo = " 	elements with \"display: list-item\" 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class MarginRight{
    public static final  String marginRight = "margin-right";
    public static final  String inherit = "inherit";
    public static final  String marginWidthValue = "0px";
    public static final  String initialValue = "0";
    public static final  String marginRightValue = initialValue;
    public static final  String appliesTo = " 	all elements except elements with table display types other than table-caption, table and inline-table 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to width of containing block 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class MarginLeft{
    public static final  String marginLeft = "margin-left";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "0";
    public static final  String marginLeftValue = initialValue;
    public static final  String appliesTo = " 	all elements except elements with table display types other than table-caption, table and inline-table 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to width of containing block 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class MarginTop{
    public static final  String marginTop = "margin-top";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "0";
    public static final  String marginTopValue = initialValue;
    public static final  String appliesTo = " 	all elements except elements with table display types other than table-caption, table and inline-table 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to width of containing block 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class MarginBottom{
    public static final  String marginBottom = "margin-bottom";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "0";
    public static final  String marginBottomValue = initialValue;
    public static final  String appliesTo = " 	all elements except elements with table display types other than table-caption, table and inline-table 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to width of containing block 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class Margin{
    public static final  String margin = "margin";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "0";
    public static final  String marginValue = initialValue;
    public static final  String appliesTo = " 	all elements except elements with table display types other than table-caption, table and inline-table 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to width of containing block 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class MaxHeight{
    public static final  String maxHeight = "max-height";
    public static final  String length = "length";
    public static final  String percentage = "percentage";
    public static final  String none = "none";
    public static final  String inherit = "inherit";
    public static final  String initialValue = none;
    public static final  String maxHeightValue = initialValue;
    public static final  String appliesTo = " 	all elements but non-replaced inline elements, table columns, and column groups 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	see prose 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class MaxWidth{
    public static final  String maxWidth = "max-width";
    public static final  String length = "length";
    public static final  String percentage = "percentage";
    public static final  String none = "none";
    public static final  String inherit = "inherit";
    public static final  String initialValue = none;
    public static final  String maxWidthValue = initialValue;
    public static final  String appliesTo = " 	all elements but non-replaced inline elements, table rows, and row groups 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to width of containing block 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class MinHeight{
    public static final  String minHeight = "min-height";
    public static final  String length = "length";
    public static final  String percentage = "percentage";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "0";
    public static final  String minHeightValue = initialValue;
    public static final  String appliesTo = " 	all elements but non-replaced inline elements, table columns, and column groups 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	see prose 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class MinWidth{
    public static final  String minWidth = "min-width";
    public static final  String length = "length";
    public static final  String percentage = "percentage";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "0";
    public static final  String minWidthValue = initialValue;
    public static final  String appliesTo = " 	all elements but non-replaced inline elements, table rows, and row groups 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to width of containing block 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class Orphans{
    public static final  String orphans = "orphans";
    public static final  String integer = "integer";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "2";
    public static final  String orphansValue = initialValue;
    public static final  String appliesTo = " 	block-level elements 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual, MediaGroups.paged});
}
public static class OutlineColor{
    public static final  String outlineColor = "outline-color";
    public static final  String color = "color";
    public static final  String invert = "invert";
    public static final  String inherit = "inherit";
    public static final  String initialValue = invert;
    public static final  String outlineColorValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual, MediaGroups.interactive});
}
public static class OutlineStyle{
    public static final  String outlineStyle = "outline-style";
    public static final  String borderStyle = "border-style";
    public static final  String inherit = "inherit";
    public static final  String none = "none";
    public static final  String initialValue = none;
    public static final  String outlineStyleValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual, MediaGroups.interactive});
}
public static class OutlineWidth{
    public static final  String outlineWidth = "outline-width";
    public static final  String borderWidth = "border-width";
    public static final  String inherit = "inherit";
    public static final  String medium = "medium";
    public static final  String initialValue = medium;
    public static final  String outlineWidthValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual, MediaGroups.interactive});
}
public static class Outline{
    public static final  String outline = "outline";
    public static final  String outlineColor = "outline-color";
    public static final  String outlineStyle = "outline-style";
    public static final  String outlineWidth = "outline-width";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "none";
    public static final  String outlineValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual, MediaGroups.interactive});
}
public static class Overflow{
    public static final  String overflow = "overflow";
    public static final  String visible = "visible";
    public static final  String hidden = "hidden";
    public static final  String scroll = "scroll";
    public static final  String auto = "auto";
    public static final  String inherit = "inherit";
    public static final  String initialValue = visible;
    public static final  String overflowValue = initialValue;
    public static final  String appliesTo = " 	non-replaced block-level elements, table cells, and inline-block elements 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class PaddingTop{
    public static final  String paddingTop = "padding-top";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "0";
;
    public static final  String paddingTopValue = initialValue;
    public static final  String appliesTo = " 	all elements except table-row-group, table-header-group, table-footer-group, table-row, table-column-group and table-column 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	refer to width of containing block 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 		 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class PaddingRight{
    public static final  String paddingRight = "padding-right";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "0";
    public static final  String paddingRightValue = initialValue;
    public static final  String appliesTo = " 	all elements except table-row-group, table-header-group, table-footer-group, table-row, table-column-group and table-column 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to width of containing block 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class PaddingBottom{
    public static final  String paddingBottom = "padding-bottom";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "0";
    public static final  String paddingBottomValue = initialValue;
    public static final  String appliesTo = " 	all elements except table-row-group, table-header-group, table-footer-group, table-row, table-column-group and table-column 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to width of containing block 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class PaddingLeft{
    public static final  String paddingLeft = "padding-left";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "0";
    public static final  String paddingLeftValue = initialValue;
    public static final  String appliesTo = " 	all elements except table-row-group, table-header-group, table-footer-group, table-row, table-column-group and table-column 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to width of containing block 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class Padding{
    public static final  String padding = "padding";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "0";
    public static final  String paddingValue = initialValue;
    public static final  String appliesTo = " 	all elements except table-row-group, table-header-group, table-footer-group, table-row, table-column-group and table-column 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to width of containing block 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class PageBreakAfter{
    public static final  String pageBreakAfter = "page-break-after";
    public static final  String auto = "auto";
    public static final  String always = "always";
    public static final  String avoid = "avoid";
    public static final  String left = "left";
    public static final  String right = "right";
    public static final  String inherit = "inherit";
    public static final  String initialValue = auto;
    public static final  String pageBreakAfterValue = initialValue;
    public static final  String appliesTo = " 	block-level elements 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual, MediaGroups.paged});
}
public static class PageBreakBefore{
    public static final  String pageBreakBefore = "page-break-before";
    public static final  String auto = "auto";
    public static final  String always = "always";
    public static final  String avoid = "avoid";
    public static final  String left = "left";
    public static final  String right = "right";
    public static final  String inherit = "inherit";
    public static final  String initialValue = auto;
    public static final  String pageBreakBeforeValue = initialValue;
    public static final  String appliesTo = " 	block-level elements 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual, MediaGroups.paged});
}
public static class PageBreakInside{
    public static final  String pageBreakInside = "page-break-inside";
    public static final  String avoid = "avoid";
    public static final  String auto = "auto";
    public static final  String inherit = "inherit";
    public static final  String initialValue = auto;
    public static final  String pageBreakInsideValue = initialValue;
    public static final  String appliesTo = " 	block-level elements 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual, MediaGroups.paged});
}
public static class PauseAfter{
    public static final  String pauseAfter = "pause-after";
    public static final  String time = "time";
    public static final  String percentage = "percentage";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "0";
    public static final  String pauseAfterValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	see prose 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.aural});
}
public static class PauseBefore{
    public static final  String pauseBefore = "pause-before";
    public static final  String time = "time";
    public static final  String percentage = "percentage";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "0";
    public static final  String pauseBeforeValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	see prose 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.aural});
}
public static class Pause{
    public static final  String pause = "pause";
    public static final  String time = "time";
    public static final  String percentage = "percentage";
    public static final  String inherit = "inherit";
    public static final  String initialValue = null;
    public static final  String pauseValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	see descriptions of \"pause-before\" and \"pause-after\" 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.aural});
}
public static class PitchRange{
    public static final  String pitchRange = "pitch-range";
    public static final  String number = "number";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "50";
    public static final  String pitchRangeValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.aural});
}
public static class Pitch{
    public static final  String pitch = "pitch";
    public static final  String frequency = "frequency";
    public static final  String xLow = "x-low";
    public static final  String low = "low";
    public static final  String medium = "medium";
    public static final  String high = "high";
    public static final  String xHigh = "x-high";
    public static final  String inherit = "inherit";
    public static final  String initialValue = medium;
    public static final  String pitchValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.aural});
}
public static class PlayDuring{public static final  String playDuring = "play-during";
    public static final  String uri = "uri";
    public static final  String mix = "mix";
    public static final  String repeat = "repeat";
    public static final  String auto = "auto";
    public static final  String none = "none";
    public static final  String inherit = "inherit";
    public static final  String initialValue = auto;
    public static final  String playDuringValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.aural});
}
public static class Position{
    public static final  String position = "position";
    public static final  String cssStatic  = "static";
    public static final  String relative = "relative";
    public static final  String absolute = "absolute";
    public static final  String fixed = "fixed";
    public static final  String inherit = "inherit";
    public static final  String initialValue = cssStatic;
    public static final  String positionValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class Quotes{
    public static final  String quotes = "quotes";
    public static final  String string = "string";
    public static final  String none = "none";
    public static final  String inherit = "inherit";
    public static final  String initialValue = null;
    public static final  String quotesValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class Richness{
    public static final  String richness = "richness";
    public static final  String number = "number";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "50";
    public static final  String richnessValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.aural});
}
public static class Right{
    public static final  String right = "right";
    public static final  String length = "length";
    public static final  String percentage = "percentage";
    public static final  String auto = "auto";
    public static final  String inherit = "inherit";
    public static final  String initialValue = auto;
    public static final  String rightValue = initialValue;
    public static final  String appliesTo = " 	positioned elements 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to width of containing block 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class SpeakHeader{
    public static final  String speakHeader = "speak-header";
    public static final  String once = "once";
    public static final  String always = "always";
    public static final  String inherit = "inherit";
    public static final  String initialValue = once;
    public static final  String speakHeaderValue = initialValue;
    public static final  String appliesTo = " 	elements that have table header information 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.aural});
}
public static class SpeakNumeral{
    public static final  String speakNumeral = "speak-numeral";
    public static final  String digits = "digits";
    public static final  String continuous = "continuous";
    public static final  String inherit = "inherit";
    public static final  String initialValue = continuous;
    public static final  String speakNumeralValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.aural});
}
public static class SpeakPunctuation{
    public static final  String speakPunctuation = "speak-punctuation";
    public static final  String code = "code";
    public static final  String none = "none";
    public static final  String inherit = "inherit";
    public static final  String initialValue = none;
    public static final  String speakPunctuationValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.aural});
}
public static class Speak{
    public static final  String speak = "speak";
    public static final  String normal = "normal";
    public static final  String none = "none";
    public static final  String spellOut = "spell-out";
    public static final  String inherit = "inherit";
    public static final  String initialValue = normal;
    public static final  String speakValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.aural});
}
public static class SpeechRate{
    public static final  String speechRate = "speech-rate";
    public static final  String number = "number";
    public static final  String xSlow = "x-slow";
    public static final  String slow = "slow";
    public static final  String medium = "medium";
    public static final  String fast = "fast";
    public static final  String xFast = "x-fast";
    public static final  String faster = "faster";
    public static final  String slower = "slower";
    public static final  String inherit = "inherit";
    public static final  String initialValue = medium;
    public static final  String speechRateValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.aural});
}
public static class Stress{
    public static final  String stress = "stress";
    public static final  String number = "number";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "50";
    public static final  String stressValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.aural});
}
public static class TableLayout{
    public static final  String tableLayout = "table-layout";
    public static final  String auto = "auto";
    public static final  String fixed = "fixed";
    public static final  String inherit = "inherit";
    public static final  String initialValue = auto;
    public static final  String tableLayoutValue = initialValue;
    public static final  String appliesTo = " 	\"table\" and \"inline-table\" elements 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class TextAlign{
    public static final  String textAlign = "text-align";
    public static final  String left = "left";
    public static final  String right = "right";
    public static final  String center = "center";
    public static final  String justify = "justify";
    public static final  String inherit = "inherit";
    public static final  String initialValue = left;
    public static final  String textAlignValue = initialValue;
    public static final  String appliesTo = " 	block-level elements, table cells and inline blocks 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class TextDecoration{
    public static final  String textDecoration = "text-decoration";
    public static final  String none = "none";
    public static final  String underline = "underline";
    public static final  String overline = "overline";
    public static final  String lineThrough = "line-through";
    public static final  String blink = "blink";
    public static final  String inherit = "inherit";
    public static final  String initialValue = none;
    public static final  String textDecorationValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no (see prose) 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class TextIndent{
    public static final  String textIndent = "text-indent";
    public static final  String length = "length";
    public static final  String percentage = "percentage";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "0";
    public static final  String textIndentValue = initialValue;
    public static final  String appliesTo = " 	block-level elements, table cells and inline blocks 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to width of containing block 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class TextTransform{
    public static final  String textTransform = "text-transform";
    public static final  String capitalize = "capitalize";
    public static final  String uppercase = "uppercase";
    public static final  String lowercase = "lowercase";
    public static final  String none = "none";
    public static final  String inherit = "inherit";
    public static final  String initialValue = none;
    public static final  String textTransformValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class Top{
    public static final  String top = "top";
    public static final  String length = "length";
    public static final  String percentage = "percentage";
    public static final  String auto = "auto";
    public static final  String inherit = "inherit";
    public static final  String initialValue = auto;
    public static final  String topValue = initialValue;
    public static final  String appliesTo = " 	positioned elements 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to height of containing block 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class UnicodeBidi{
    public static final  String unicodeBidi = "unicode-bidi";
    public static final  String normal = "normal";
    public static final  String embed = "embed";
    public static final  String bidiOverride = "bidi-override";
    public static final  String inherit = "inherit";
    public static final  String initialValue = normal;
    public static final  String unicodeBidiValue = initialValue;
    public static final  String appliesTo = " 	all elements, but see prose 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class VerticalAlign{
    public static final  String verticalAlign = "vertical-align";
    public static final  String baseline = "baseline";
    public static final  String sub = "sub";
    public static final  String cssSuper = "super";
    public static final  String top = "top";
    public static final  String textTop = "text-top";
    public static final  String middle = "middle";
    public static final  String bottom = "bottom";
    public static final  String textBottom = "text-bottom";
    public static final  String percentage = "percentage";
    public static final  String length = "length";
    public static final  String inherit = "inherit";
    public static final  String initialValue = baseline;
    public static final  String verticalAlignValue = initialValue;
    public static final  String appliesTo = " 	inline-level and \"table-cell\" elements 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to the \"line-height\" of the element itself 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class Visibility{
    public static final  String visibility = "visibility";
    public static final  String visible = "visible";
    public static final  String hidden = "hidden";
    public static final  String collapse = "collapse";
    public static final  String inherit = "inherit";
    public static final  String initialValue = visible;
    public static final  String visibilityValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class VoiceFamily{
    public static final  String voiceFamily = "voice-family";
    public static final  String specificVoice = "specific-voice";
    public static final  String genericVoice = "generic-voice";
    public static final  String inherit = "inherit";
    public static final  String initialValue = null;
    public static final  String voiceFamilyValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.aural});
}
public static class Volume{
    public static final  String volume = "volume";
    public static final  String number = "number";
    public static final  String percentage = "percentage";
    public static final  String silent = "silent";
    public static final  String xSoft = "x-soft";
    public static final  String soft = "soft";
    public static final  String medium = "medium";
    public static final  String loud = "loud";
    public static final  String xLoud = "x-loud";
    public static final  String inherit = "inherit";
    public static final  String initialValue = medium;
    public static final  String volumeValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to inherited value 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.aural});
}
public static class WhiteSpace{
    public static final  String whiteSpace = "white-space";
    public static final  String normal = "normal";
    public static final  String pre = "pre";
    public static final  String nowrap = "nowrap";
    public static final  String preWrap = "pre-wrap";
    public static final  String preLine = "pre-line";
    public static final  String inherit = "inherit";
    public static final  String initialValue = normal;
    public static final  String whiteSpaceValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class Widows{
    public static final  String widows = "widows";
    public static final  String integer = "integer";
    public static final  String inherit = "inherit";
    public static final  String initialValue = "2";
    public static final  String widowsValue = initialValue;
    public static final  String appliesTo = " 	block-level elements 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual, MediaGroups.paged});
}
public static class Width{
    public static final  String width = "width";
    public static final  String length = "length";
    public static final  String percentage = "percentage";
    public static final  String auto = "auto";
    public static final  String inherit = "inherit";
    public static final  String initialValue = auto;
    public static final  String widthValue = initialValue;
    public static final  String appliesTo = " 	all elements but non-replaced inline elements, table rows, and row groups 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	refer to width of containing block 	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class WordSpacing{
    public static final  String wordSpacing = "word-spacing";
    public static final  String normal = "normal";
    public static final  String length = "length";
    public static final  String inherit = "inherit";
    public static final  String initialValue = normal;
    public static final  String wordSpacingValue = initialValue;
    public static final  String appliesTo = " 	  	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	yes 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}
public static class ZIndex{
    public static final  String zIndex = "z-index";
    public static final  String auto = "auto";
    public static final  String integer = "integer";
    public static final  String inherit = "inherit";
    public static final  String initialValue = auto;
    public static final  String zIndexValue = initialValue;
    public static final  String appliesTo = " 	positioned elements 	 ";
    public static final  String cssInherited = " 	inherited	 ";
    public static final  String inherited = " 	no 	 ";
    public static final  String cssPercentages = " 	percentages	 ";
    public static final  String percentages = " 	  	 ";
    public static final  List cssMediaGroups = Arrays.asList(new String[] {MediaGroups.visual});
}

public static class Units{
    public static final  String px = "px";
    public static final  String em = "em";
    public static final  String pct = "%";
}

}


    
    




