package hva.app.main;

import hva.core.Animal;
import hva.core.Employee;
import hva.core.HotelManager;
import hva.core.exception.UnknownAnimalIdException;
import hva.core.exception.UnknownHabitatIdException;
import hva.core.exception.UnknownSpeciesIdException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for show the global satisfation of the current zoo hotel.
 **/
class DoShowGlobalSatisfaction extends Command<HotelManager> {
  DoShowGlobalSatisfaction(HotelManager receiver) {
    super(hva.app.main.Label.SHOW_GLOBAL_SATISFACTION, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
  double valor = 0;
    for(Animal a : _receiver.getHotel().get_animais()){
      valor += _receiver.getHotel().getSatisfacaoAnimal(a);
    }
    for(Employee e : _receiver.getHotel().get_funcionarios()){
      if(e.get_tipo().equals("VET")){
        valor += _receiver.getHotel().getSatisfacaoVet(e);
      }
      if(e.get_tipo().equals("TRT")){
        valor += _receiver.getHotel().getSatisfacaoTrt(e);
      }
    }
    _display.addLine((int) valor);
    _display.display();
  }
}
