/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clickdigitalsolutions.rentverticalmenu;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Icons extends StackPane {


    private AnimationType type;
    
    public ContextMenu stickyMenu = new ContextMenu();
    
    public MenuItem stickyMenuItem = new MenuItem("Delete Sticky Note");
    
    EventHandler<MouseEvent> keyEventHandler =
        (MouseEvent keyEvent) -> {
            addJumpAnimation();
    };
    
    
    
    public Icons(String number) {
        Label lab = new Label(number);
        stickyMenu.getItems().add(stickyMenuItem);
        lab.setContextMenu(stickyMenu);
        lab.setStyle("-fx-text-fill:white");
        Rectangle rect = new Rectangle(15, 15);
        rect.setFill(Color.rgb(0, 122, 255));
        rect.setArcHeight(2);
        rect.setArcHeight(2);
        rect.setStrokeWidth(2.0);
        rect.setStyle("-fx-background-insets: 0 0 -1 0, 0, 1, 2;");
        getChildren().addAll(rect, lab);
    }

    private void addBlinkAnimation() {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        final KeyValue kv = new KeyValue(this.opacityProperty(), 0.0);
        final KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }
    
    private void addJumpAnimation() {
        final TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200), this);
        final double start = 0.0;
        final double end = start - 4.0;
        translateTransition.setFromY(start);
        translateTransition.setToY(end);
        translateTransition.setCycleCount(-1);
        translateTransition.setAutoReverse(true);
        translateTransition.setInterpolator(Interpolator.EASE_BOTH);
        translateTransition.play();
    }
    
    
    public enum AnimationType {

        BLINK, JUMP, NONE;
    };
    
    private void addAnimation() {
        switch (type) {
            case BLINK:
                addBlinkAnimation();
                break;
            case JUMP:
                addJumpAnimation();
                break;
            case NONE:
                break;
            default:
                break;
        }
    }
}
