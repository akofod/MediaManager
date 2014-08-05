import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class MediaManager implements ActionListener{

    ArrayList<MediaItem> itemList;
    MediaDAO mDAO;
    
    JFrame appFrame;
    Container cPane;
    
    JLabel lbName, lbYear, lbComments, lbCurValue, 
    lbMediaType, lbGenre, lbPurLoc, lbPurDate, lbPurPrice;
    
    JTextField tfName, tfYear, tfComments, tfCurValue, 
    tfMediaType, tfGenre, tfPurLoc, tfPurDate, tfPurPrice;
    
    JButton btSave, btDelete, btUpdate, btClear, btSearch,
    btForward, btBack, btExit;
    
    private String name, comments, mediaType, genre, purLoc, purDate;
    @SuppressWarnings("unused")
	private int year, id, recordNumber;
    private double purPrice, curValue;
    
    public MediaManager() {
        name = "";
        comments = "";
        mediaType = "";
        genre = "";
        purLoc = "";
        
        year = 0;
        id = 0;
        purPrice = 0.0;
        curValue = 0.0;
        purDate = "";
        recordNumber = -1;
        
        createGUI();
        
        itemList = new ArrayList<MediaItem>();
        
        mDAO = new MediaDAO();
    }
    
    public void createGUI() {
        appFrame = new JFrame("Media Manager");
        cPane = appFrame.getContentPane();
        cPane.setLayout(new GridBagLayout());
        
        setComponents();
        
        appFrame.setSize(450, 450);
        appFrame.setResizable(false);
        appFrame.setVisible(true);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void setComponents() {
        lbName = new JLabel("Name");
        lbYear = new JLabel("Year Released"); 
        lbComments = new JLabel("Comments");
        lbCurValue = new JLabel("Current Value");
        lbMediaType = new JLabel("Media Type");
        lbGenre = new JLabel("Genre");
        lbPurLoc = new JLabel("Purchase Location");
        lbPurDate = new JLabel("Purchase Date");
        lbPurPrice = new JLabel("Purchase Price");
        
        tfName = new JTextField(10);
        tfYear = new JTextField(4);
        tfComments = new JTextField(20);
        tfCurValue = new JTextField(7);
        tfMediaType = new JTextField(10);
        tfGenre = new JTextField(10);
        tfPurLoc = new JTextField(10);
        tfPurDate = new JTextField(10);
        tfPurPrice = new JTextField(7);
        
        btSave = new JButton("Save");
        btDelete = new JButton("Delete");
        btUpdate = new JButton("Update");
        btClear = new JButton("Clear");
        btSearch = new JButton("Search");
        btForward = new JButton(">>");
        btBack = new JButton("<<");
        btExit = new JButton("Exit");
        
        GridBagConstraints gridBagConstraintsx01 = new GridBagConstraints();
        gridBagConstraintsx01.gridx = 0;
        gridBagConstraintsx01.gridy = 0;
        gridBagConstraintsx01.insets = new Insets(5,5,5,5);
        cPane.add(lbName, gridBagConstraintsx01);
        
        GridBagConstraints gridBagConstraintsx02 = new GridBagConstraints();
        gridBagConstraintsx02.gridx = 1;
        gridBagConstraintsx02.insets = new Insets(5,5,5,5);
        gridBagConstraintsx02.gridy = 0;
        gridBagConstraintsx02.gridwidth = 3;
        gridBagConstraintsx02.fill = GridBagConstraints.BOTH;
        cPane.add(tfName, gridBagConstraintsx02);
        
        GridBagConstraints gridBagConstraintsx03 = new GridBagConstraints();
        gridBagConstraintsx03.gridx = 0;
        gridBagConstraintsx03.insets = new Insets(5,5,5,5);
        gridBagConstraintsx03.gridy = 1;
        cPane.add(lbYear, gridBagConstraintsx03);
        
        GridBagConstraints gridBagConstraintsx04 = new GridBagConstraints();
        gridBagConstraintsx04.gridx = 1;
        gridBagConstraintsx04.insets = new Insets(5,5,5,5);
        gridBagConstraintsx04.gridy = 1;
        gridBagConstraintsx04.gridwidth = 2;
        gridBagConstraintsx04.fill = GridBagConstraints.BOTH;
        cPane.add(tfYear, gridBagConstraintsx04);
        
        GridBagConstraints gridBagConstraintsx05 = new GridBagConstraints();
        gridBagConstraintsx05.gridx = 0;
        gridBagConstraintsx05.insets = new Insets(5,5,5,5);
        gridBagConstraintsx05.gridy = 2;
        cPane.add(lbMediaType, gridBagConstraintsx05);
        
        GridBagConstraints gridBagConstraintsx06 = new GridBagConstraints();
        gridBagConstraintsx06.gridx = 1;
        gridBagConstraintsx06.insets = new Insets(5,5,5,5);
        gridBagConstraintsx06.gridy = 2;
        gridBagConstraintsx06.gridwidth = 2;
        gridBagConstraintsx06.fill = GridBagConstraints.BOTH;
        cPane.add(tfMediaType, gridBagConstraintsx06);
        
        GridBagConstraints gridBagConstraintsx07 = new GridBagConstraints();
        gridBagConstraintsx07.gridx = 0;
        gridBagConstraintsx07.insets = new Insets(5,5,5,5);
        gridBagConstraintsx07.gridy = 3;
        cPane.add(lbGenre, gridBagConstraintsx07);
        
        GridBagConstraints gridBagConstraintsx08 = new GridBagConstraints();
        gridBagConstraintsx08.gridx = 1;
        gridBagConstraintsx08.insets = new Insets(5,5,5,5);
        gridBagConstraintsx08.gridy = 3;
        gridBagConstraintsx08.gridwidth = 2;
        gridBagConstraintsx08.fill = GridBagConstraints.BOTH;
        cPane.add(tfGenre, gridBagConstraintsx08);
        
        GridBagConstraints gridBagConstraintsx09 = new GridBagConstraints();
        gridBagConstraintsx09.gridx = 0;
        gridBagConstraintsx09.insets = new Insets(5,5,5,5);
        gridBagConstraintsx09.gridy = 4;
        cPane.add(lbPurDate, gridBagConstraintsx09);
        
        GridBagConstraints gridBagConstraintsx10 = new GridBagConstraints();
        gridBagConstraintsx10.gridx = 1;
        gridBagConstraintsx10.insets = new Insets(5,5,5,5);
        gridBagConstraintsx10.gridy = 4;
        gridBagConstraintsx10.gridwidth = 2;
        gridBagConstraintsx10.fill = GridBagConstraints.BOTH;
        cPane.add(tfPurDate, gridBagConstraintsx10);
        
        GridBagConstraints gridBagConstraintsx11 = new GridBagConstraints();
        gridBagConstraintsx11.gridx = 0;
        gridBagConstraintsx11.insets = new Insets(5,5,5,5);
        gridBagConstraintsx11.gridy = 5;
        cPane.add(lbPurPrice, gridBagConstraintsx11);
        
        GridBagConstraints gridBagConstraintsx12 = new GridBagConstraints();
        gridBagConstraintsx12.gridx = 1;
        gridBagConstraintsx12.insets = new Insets(5,5,5,5);
        gridBagConstraintsx12.gridy = 5;
        gridBagConstraintsx12.gridwidth = 2;
        gridBagConstraintsx12.fill = GridBagConstraints.BOTH;
        cPane.add(tfPurPrice, gridBagConstraintsx12);
        
        GridBagConstraints gridBagConstraintsx13 = new GridBagConstraints();
        gridBagConstraintsx13.gridx = 0;
        gridBagConstraintsx13.insets = new Insets(5,5,5,5);
        gridBagConstraintsx13.gridy = 6;
        cPane.add(lbPurLoc, gridBagConstraintsx13);
        
        GridBagConstraints gridBagConstraintsx14 = new GridBagConstraints();
        gridBagConstraintsx14.gridx = 1;
        gridBagConstraintsx14.insets = new Insets(5,5,5,5);
        gridBagConstraintsx14.gridy = 6;
        gridBagConstraintsx14.gridwidth = 2;
        gridBagConstraintsx14.fill = GridBagConstraints.BOTH;
        cPane.add(tfPurLoc, gridBagConstraintsx14);
        
        GridBagConstraints gridBagConstraintsx15 = new GridBagConstraints();
        gridBagConstraintsx15.gridx = 0;
        gridBagConstraintsx15.insets = new Insets(5,5,5,5);
        gridBagConstraintsx15.gridy = 7;
        cPane.add(lbCurValue, gridBagConstraintsx15);
        
        GridBagConstraints gridBagConstraintsx16 = new GridBagConstraints();
        gridBagConstraintsx16.gridx = 1;
        gridBagConstraintsx16.insets = new Insets(5,5,5,5);
        gridBagConstraintsx16.gridy = 7;
        gridBagConstraintsx16.gridwidth = 2;
        gridBagConstraintsx16.fill = GridBagConstraints.BOTH;
        cPane.add(tfCurValue, gridBagConstraintsx16);
        
        GridBagConstraints gridBagConstraintsx17 = new GridBagConstraints();
        gridBagConstraintsx17.gridx = 0;
        gridBagConstraintsx17.insets = new Insets(5,5,5,5);
        gridBagConstraintsx17.gridy = 8;
        cPane.add(lbComments, gridBagConstraintsx17);
        
        GridBagConstraints gridBagConstraintsx18 = new GridBagConstraints();
        gridBagConstraintsx18.gridx = 1;
        gridBagConstraintsx18.insets = new Insets(5,5,5,5);
        gridBagConstraintsx18.gridy = 8;
        gridBagConstraintsx18.gridwidth = 2;
        gridBagConstraintsx18.fill = GridBagConstraints.BOTH;
        cPane.add(tfComments, gridBagConstraintsx18);
        
        GridBagConstraints gridBagConstraintsx19 = new GridBagConstraints();
        gridBagConstraintsx19.gridx = 0;
        gridBagConstraintsx19.gridy = 10;
        gridBagConstraintsx19.insets = new Insets(5,5,5,5);
        cPane.add(btSave, gridBagConstraintsx19);
        
        GridBagConstraints gridBagConstraintsx20 = new GridBagConstraints();
        gridBagConstraintsx20.gridx = 1;
        gridBagConstraintsx20.gridy = 10;
        gridBagConstraintsx20.insets = new Insets(5,5,5,5);
        cPane.add(btUpdate, gridBagConstraintsx20);
        
        GridBagConstraints gridBagConstraintsx21 = new GridBagConstraints();
        gridBagConstraintsx21.gridx = 2;
        gridBagConstraintsx21.gridy = 10;
        gridBagConstraintsx21.insets = new Insets(5,5,5,5);
        cPane.add(btDelete, gridBagConstraintsx21);
        
        GridBagConstraints gridBagConstraintsx22 = new GridBagConstraints();
        gridBagConstraintsx22.gridx = 0;
        gridBagConstraintsx22.gridy = 11;
        gridBagConstraintsx22.insets = new Insets(5,5,5,5);
        cPane.add(btBack, gridBagConstraintsx22);
        
        GridBagConstraints gridBagConstraintsx23 = new GridBagConstraints();
        gridBagConstraintsx23.gridx = 2;
        gridBagConstraintsx23.gridy = 11;
        gridBagConstraintsx23.insets = new Insets(5,5,5,5);
        cPane.add(btForward, gridBagConstraintsx23);
        
        GridBagConstraints gridBagConstraintsx24 = new GridBagConstraints();
        gridBagConstraintsx24.gridx = 0;
        gridBagConstraintsx24.gridy = 12;
        gridBagConstraintsx24.insets = new Insets(5,5,5,5);
        cPane.add(btClear, gridBagConstraintsx24);
        
        GridBagConstraints gridBagConstraintsx25 = new GridBagConstraints();
        gridBagConstraintsx25.gridx = 1;
        gridBagConstraintsx25.gridy = 12;
        gridBagConstraintsx25.insets = new Insets(5,5,5,5);
        cPane.add(btSearch, gridBagConstraintsx25);
        
        GridBagConstraints gridBagConstraintsx26 = new GridBagConstraints();
        gridBagConstraintsx26.gridx = 2;
        gridBagConstraintsx26.gridy = 12;
        gridBagConstraintsx26.insets = new Insets(5,5,5,5);
        cPane.add(btExit, gridBagConstraintsx26);
                
        btSave.addActionListener(this);
        btDelete.addActionListener(this);
        btUpdate.addActionListener(this);
        btClear.addActionListener(this);
        btSearch.addActionListener(this);
        btForward.addActionListener(this);
        btBack.addActionListener(this);
        btExit.addActionListener(this);        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource () == btSave){
            saveItem();
            clear();
        }

        else if (e.getSource() == btDelete){
            deleteItem();
            clear();
        }

        else if (e.getSource() == btUpdate){
            updateItem();
            clear();
        }

        else if (e.getSource() == btSearch){
            searchItem();
        }

        else if (e.getSource() == btForward){
            nextRecord();
        }

        else if (e.getSource() == btBack){
            previousRecord();
        }

        else if (e.getSource() == btClear){
            clear();
        }

        else if (e.getSource() == btExit){
            System.exit(0);
        }        
    }
    
    public void saveItem() {
        name = tfName.getText();
        comments = tfComments.getText();
        mediaType = tfMediaType.getText();
        genre = tfGenre.getText();
        purLoc = tfPurLoc.getText();
        purDate = tfPurDate.getText();
        
        try {
            year = Integer.parseInt(tfYear.getText());
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please enter the year of release.");
        }
        
        try {
            purPrice = Double.parseDouble(tfPurPrice.getText());
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please enter the purchase price.");
        }
        
        try {
            curValue = Double.parseDouble(tfCurValue.getText());
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please enter the current value of the item.");
        }
        
        if (name.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter the name of this item.");
        }
        else {
            MediaItem item = new MediaItem(name, comments, mediaType, genre, 
                    purLoc, year, purPrice, curValue, purDate);
            mDAO.saveItem(item);
            JOptionPane.showMessageDialog(null, "The item has been saved.");
        }
    }
    
    public void deleteItem(){

        name = tfName.getText();
        if(name.equals("")){
            JOptionPane.showMessageDialog(null,"Please enter item name to delete.");
        }
        else{
            int numberOfDeleted = mDAO.removeItem(name);
            JOptionPane.showMessageDialog(null, numberOfDeleted + " item(s) deleted.");
        }
    }
    
    public void updateItem(){
        if (recordNumber >= 0 && recordNumber < itemList.size())
        {
            MediaItem item = (MediaItem)itemList.get(recordNumber);

            int id = item.getId();

            name = tfName.getText();
            comments = tfComments.getText();
            mediaType = tfMediaType.getText();
            genre = tfGenre.getText();
            purLoc = tfPurLoc.getText();
            purDate = tfPurDate.getText();
            year = Integer.parseInt(tfYear.getText());
            purPrice = Double.parseDouble(tfPurPrice.getText());
            curValue = Double.parseDouble(tfCurValue.getText());

            item = new MediaItem(id, name, comments, mediaType, genre, 
                    purLoc, year, purPrice, curValue, purDate);
            mDAO.updateItem(item);

            JOptionPane.showMessageDialog(null, "Item info was updated successfully.");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No record to update");
        }
    }

    public void searchItem() {

        name = tfName.getText();
        itemList.clear();

        recordNumber = 0;

        if(name.equals("")){
            JOptionPane.showMessageDialog(null,"Please enter item name to search.");
        }
        else{
            itemList = mDAO.searchItem(name);

            if(itemList.size() == 0){
                JOptionPane.showMessageDialog(null, "No records found.");
                clear();
            }
            else
            {
                MediaItem item = (MediaItem) itemList.get(recordNumber);

                tfName.setText(item.getName());
                tfComments.setText(item.getComments());
                tfMediaType.setText(item.getMediaType());
                tfGenre.setText(item.getGenre());
                tfPurLoc.setText(item.getPurLoc());
                tfPurDate.setText(item.getPurDate());
                tfYear.setText(String.valueOf(item.getYear()));
                tfPurPrice.setText(String.valueOf(item.getPurPrice()));
                tfCurValue.setText(String.valueOf(item.getCurValue()));
            }
        }

    }
    
    public void nextRecord(){

        recordNumber++;

        if (recordNumber >= itemList.size()){
            JOptionPane.showMessageDialog(null, "No more records to display.");
            btForward.setEnabled(false);
            btBack.setEnabled(true);
            recordNumber -- ;
        }
        else {
            btBack.setEnabled(true);
            MediaItem item = (MediaItem) itemList.get(recordNumber);

            tfName.setText(item.getName());
            tfComments.setText(item.getComments());
            tfMediaType.setText(item.getMediaType());
            tfGenre.setText(item.getGenre());
            tfPurLoc.setText(item.getPurLoc());
            tfPurDate.setText(item.getPurDate());
            tfYear.setText(String.valueOf(item.getYear()));
            tfPurPrice.setText(String.valueOf(item.getPurPrice()));
            tfCurValue.setText(String.valueOf(item.getCurValue()));
        }
    }
    
    public void previousRecord(){

        recordNumber--;

        if (recordNumber < 0 ){
            JOptionPane.showMessageDialog(null, "You have reached begining " +
                    "of search results");
            btForward.setEnabled(true);
            btBack.setEnabled(false);
            recordNumber++;
        }
        else {
            btForward.setEnabled(true);
            MediaItem item = (MediaItem) itemList.get(recordNumber);

            tfName.setText(item.getName());
            tfComments.setText(item.getComments());
            tfMediaType.setText(item.getMediaType());
            tfGenre.setText(item.getGenre());
            tfPurLoc.setText(item.getPurLoc());
            tfPurDate.setText(item.getPurDate());
            tfYear.setText(String.valueOf(item.getYear()));
            tfPurPrice.setText(String.valueOf(item.getPurPrice()));
            tfCurValue.setText(String.valueOf(item.getCurValue()));
        }

    }

    public void clear(){

        name = "";
        comments = "";
        mediaType = "";
        genre = "";
        purLoc = "";
        
        year = 0;
        id = 0;
        purPrice = 0.0;
        curValue = 0.0;
        purDate = "";
        recordNumber = -1;
        itemList.clear();
        btForward.setEnabled(true);
        btBack.setEnabled(true);
        
        tfName.setText(name);
        tfComments.setText(comments);
        tfMediaType.setText(mediaType);
        tfGenre.setText(genre);
        tfPurLoc.setText(purLoc);
        tfPurDate.setText(purDate);
        tfYear.setText(String.valueOf(year));
        tfPurPrice.setText(String.valueOf(purPrice));
        tfCurValue.setText(String.valueOf(curValue));
        appFrame.repaint();
    }
    
    public static void main(String[] args) {
        new MediaManager();
    }

}
