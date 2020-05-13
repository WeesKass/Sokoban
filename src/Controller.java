import java.awt.event.*;


public class Controller implements KeyListener, ActionListener{

    private Model model;

    public Controller(Viewer viewer) {
        model = new Model(viewer);
    }



    public Model getModel() {
        return model;
    }


    @Override
    public void keyPressed(KeyEvent keyEvent) {

        int keyCode = keyEvent.getKeyCode();
        String direction;

        switch (keyCode) {
            case 39:
            case 68:
                direction = "Right";
                break;
            case 37:
            case 65:
                direction = "Left";
                break;
            case 38:
            case 87:
                direction = "Up";
                break;
            case 40:
            case 83:
                direction = "Down";
                break;
            case 82:
                model.doAction("restart");
                return;
            default:
                System.out.println("unhandled");
                return;
        }
        model.move(direction);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        model.doAction(actionEvent.getActionCommand());
        model.getViewer().getCanvas().requestFocus();
    }
}
