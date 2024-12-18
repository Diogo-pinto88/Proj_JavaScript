package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.Register;
import hva.core.Vaccine;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.List;


/**
 * Show all applied vacines by all veterinarians of this zoo hotel.
 **/
class DoShowVaccinations extends Command<Hotel> {

  DoShowVaccinations(Hotel receiver) {
    super(Label.SHOW_VACCINATIONS, receiver);
  }
  
  @Override
  protected final void execute() {
    for(Register r : _receiver.get_registos()){
      _display.addLine("REGISTO-VACINA|" + r.get_vacina().get_id() + "|" + r.get_veterinario().get_id() +
              "|" + r.get_animal().get_specie().get_id());
    }
    _display.display();
  }
}
