package hva.app.main;

import hva.app.exception.FileOpenFailedException;
import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;

import java.io.FileNotFoundException;
import java.io.IOException;
import hva.app.main.Message;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<HotelManager> {
  DoSaveFile(HotelManager receiver) {
    super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
  }

  @Override
  protected final void execute() throws CommandException{
      if(_receiver.getFilename() == null){
        _receiver.SetFilename(Form.requestString(Prompt.newSaveAs()));
      }
      try {
        _receiver.saveAs(_receiver.getFilename());
      }
      catch(Exception e) {
        throw new FileOpenFailedException(e);
      }
    }
}
