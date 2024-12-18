package hva.app.search;

import hva.core.Animal;
import hva.core.Hotel;
import hva.core.Register;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Show all vaccines applied to animals belonging to an invalid species.
 **/
class DoShowWrongVaccinations extends Command<Hotel> {

  DoShowWrongVaccinations(Hotel receiver) {
    super(Label.WRONG_VACCINATIONS, receiver);
    //FIXME add command fields
  }

  @Override
  protected void execute() throws CommandException {
    for (Register r : _receiver.get_registos()){
      if(r.Obtemdano(_receiver).equals("ERRO") | r.Obtemdano(_receiver).equals("ACIDENTE")){
        _display.addLine("REGISTO-VACINA|" + r.get_vacina().get_id() + "|" + r.get_veterinario().get_id() +
                "|" + r.get_animal().get_specie().get_id());
      }
    }
    _display.display();
  }
}
