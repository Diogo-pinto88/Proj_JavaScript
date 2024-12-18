package hva.app.main;

import hva.core.Hotel;
import hva.core.HotelManager;
import hva.core.Tree;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Command for advancing the season of the system.
 **/
class DoAdvanceSeason extends Command<HotelManager> {
  DoAdvanceSeason(HotelManager receiver) {
    super(Label.ADVANCE_SEASON, receiver);
    //FIXME add command fields
  }

  @Override
  protected final void execute() {
    for(Tree t : _receiver.getHotel().get_trees()){
      t.add_Idade();
    }
    _display.addLine(_receiver.getHotel().advanceSeason());
    _display.display();
  }
}
