package hva.app.vaccine;

import hva.core.Specie;
import hva.core.Hotel;
import hva.core.Vaccine;
import pt.tecnico.uilib.menus.Command;

import java.util.List;


/**
 * Show all vaccines.
 **/
class DoShowAllVaccines extends Command<Hotel> {

  DoShowAllVaccines(Hotel receiver) {
    super(Label.SHOW_ALL_VACCINES, receiver);
  }
  
  @Override
  protected final void execute() {
    for(Vaccine v : _receiver.get_vacinas()){
      _display.add("VACINA|" + v.get_id() + "|" + v.get_nome() + "|" + _receiver.get_registos(v).size());
      if(v.get_especies().isEmpty()) {
        _display.add("\n");
      }
      else{
        _display.add("|");
        boolean first = true;
        List<Specie> l = v.get_especies();
        for (Specie s : l) {
          if (l.size() > 1 && !first)
            _display.add(",");
          _display.add(s.get_id());
          first = false;
        }
        _display.add("\n");
      }
    }
    _display.display();
  }
}
