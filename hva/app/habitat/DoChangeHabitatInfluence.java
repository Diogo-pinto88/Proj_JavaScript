package hva.app.habitat;

import hva.core.Hotel;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.Influence;
import hva.core.Specie;
import hva.core.exception.UnknownHabitatIdException;
import hva.core.exception.UnknownSpeciesIdException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.core.Habitat;

import java.util.Objects;


/**
 * Associate (positive or negatively) a species to a given habitat.
 **/
class DoChangeHabitatInfluence extends Command<Hotel> {

  DoChangeHabitatInfluence(Hotel receiver) {
    super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
  }
  
  @Override
  protected void execute() throws CommandException {
    String idHabitat = Form.requestString(Prompt.habitatKey());
    String idSpecie = Form.requestString(hva.app.animal.Prompt.speciesKey());
    String influencia = Form.requestString(Prompt.habitatInfluence());
    while (!(influencia.equals("POS") | influencia.equals("NEG") | influencia.equals("NEU"))){
      influencia = Form.requestString(Prompt.habitatInfluence());
    }
    try{
      Habitat h = _receiver.getHabitatById(idHabitat);
      Specie e = _receiver.getSpecieById(idSpecie);
      Influence inf = _receiver.getInfluence(influencia);
      h.changeInfluence(e, inf);
    }
    catch(UnknownHabitatIdException uhie){
      throw new UnknownHabitatKeyException(uhie.getKey());
    }
    catch(UnknownSpeciesIdException usie){
      throw new UnknownSpeciesKeyException(usie.getKey());
    }
    _display.display();
  }
}
