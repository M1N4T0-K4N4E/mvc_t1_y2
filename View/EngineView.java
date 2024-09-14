package View;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EngineView extends JFrame{

    private JLabel cowCodeLabel = new JLabel("Enter Cow Code: ");
    private JTextField CowCodeInput = new JTextField(40);
    private JButton cowSendButton = new JButton("Send Cow Code");

    public EngineView(){
        JPanel EnginePanel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 200);

        EnginePanel.add(cowCodeLabel);
        EnginePanel.add(CowCodeInput);
        EnginePanel.add(cowSendButton);

        this.add(EnginePanel);
    }

    public String getCowCode(){
        return CowCodeInput.getText();
    }

    public void SendButtonListener(ActionListener sendButton){
        cowSendButton.addActionListener(sendButton);
    }

    public void resetInputs(){
        CowCodeInput.setText("");
    }

    public void showPopup(JFrame parentFrame, String result) {
        // Create the popup dialog
        JDialog dialog = new JDialog(parentFrame, "Result", true);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        // Create a panel to hold components
        JPanel panel = new JPanel();
        panel.add(new JLabel(result));

        dialog.add(panel);
        dialog.setVisible(true);
    }
}