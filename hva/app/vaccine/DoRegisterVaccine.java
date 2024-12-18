package hva.app.vaccine;

import hva.core.Hotel;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.app.exception.DuplicateVaccineKeyException;
import hva.core.exception.DuplicateVaccineIdException;
import hva.core.exception.UnknownSpeciesIdException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.List;


/**
 * Apply a vaccine to a given animal.
 **/
class DoRegisterVaccine extends Command<Hotel> {

  DoRegisterVaccine(Hotel receiver) {
    super(Label.REGISTER_VACCINE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    String idV = Form.requestString(Prompt.vaccineKey());
    String name = Form.requestString(Prompt.vaccineName());
    String[] idS = Form.requestString(Prompt.listOfSpeciesKeys()).split(",");
    try{
    if (_receiver.checkIdVaccineExists(idV))
      throw new DuplicateVaccineKeyException(idV);
    for (String id : idS) {
      if (!(_receiver.checkIdSpecieExists(id)))
        throw new UnknownSpeciesKeyException(id);
    }
    _receiver.registerVaccine(idV, name, idS);
    }
    catch (UnknownSpeciesIdException usie) {
      throw new UnknownSpeciesKeyException(usie.getKey());
    }
    catch (DuplicateVaccineIdException dvie){
      throw new DuplicateVaccineKeyException(dvie.getKey());
    }
    _display.display();
  }
}

