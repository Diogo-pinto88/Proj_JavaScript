package hva.app.main;

import hva.app.exception.FileOpenFailedException;
import hva.core.HotelManager;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Command for creating a new zoo hotel.
 **/
class DoNewFile extends Command<HotelManager> {
  DoNewFile(HotelManager receiver) {
    super(Label.NEW_FILE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    try {
      if (_receiver.getHotel() != null) {
        if (_receiver.getHotel().HasChanged()) {
          _display.clear();
          if (Form.confirm(Prompt.saveBeforeExit())) {
            if (_receiver.getFilename() == null) {
              _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
            } else {
              _receiver.saveAs(_receiver.getFilename());
            }
          }
        }
      }
    }
    catch (Exception e){
      throw new FileOpenFailedException(e);
    }
    _receiver.newHotel();
  }
}

