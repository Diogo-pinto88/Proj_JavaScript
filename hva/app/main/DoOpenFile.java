package hva.app.main;


import hva.core.HotelManager;
import hva.app.exception.FileOpenFailedException;
import hva.core.exception.UnavailableFileException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.main.Prompt;
import hva.app.main.Label;


/**
 * Command to open a file.
 */
class DoOpenFile extends Command<HotelManager> {
  DoOpenFile(HotelManager receiver) {
    super(Label.OPEN_FILE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
      String name = Form.requestString(Prompt.openFile());
      try {
          if(_receiver.getHotel() != null) {
              if(_receiver.getHotel().HasChanged()){
                  if(Form.confirm(Prompt.saveBeforeExit())){
                      if(_receiver.getFilename() == null){
                          _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
                      }
                      else {
                          _receiver.saveAs(_receiver.getFilename());
                      }
                      return;
                  }
              }
          }
      }
      catch (Exception e){
          throw new FileOpenFailedException(e);
      }
      try{
          _receiver.load(name);
      }
      catch (UnavailableFileException efe) {
        throw new FileOpenFailedException(efe);
      }
  }
}
