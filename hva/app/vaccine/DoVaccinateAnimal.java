package hva.app.vaccine;

import hva.app.exception.*;
import hva.core.*;
import hva.core.exception.UnknownAnimalIdException;
import hva.core.exception.UnknownEmployeeIdException;
import hva.core.exception.UnknownVaccineIdException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.List;


/**
 * Vaccinate by a given veterinarian a given animal with a given vaccine.
 **/
class DoVaccinateAnimal extends Command<Hotel> {
  DoVaccinateAnimal(Hotel receiver) {
    super(Label.VACCINATE_ANIMAL, receiver);
    //FIXME add command fields
  }

  @Override
  protected final void execute() throws CommandException {
    String idVa = Form.requestString(Prompt.vaccineKey());
    String idVe = Form.requestString(Prompt.veterinarianKey());
    String idA = Form.requestString(hva.app.animal.Prompt.animalKey());
    int cnt = 0;
    try {
      Employee e = _receiver.getEmployeeById(idVe);
      Animal a = _receiver.getAnimalById(idA);
      if (!_receiver.checkIdVetExists(e))
        throw new UnknownVeterinarianKeyException(idVe);
      for(String s : e.getResponsabilidadesById()){
        cnt++;
        if(a.get_specie().get_id().equals(s)){
          cnt = 0;
          Vaccine v = _receiver.getVaccineById(idVa);
          List<Specie> l = v.get_especies();
          for(Specie sp : l){
            cnt++;
            if(sp.equals(a.get_specie())){
              _receiver.adicionaVacina((Veterinarian) e, a, v);
              return;
            }
            if(cnt == l.size()){
              _display.addLine(Message.wrongVaccine(idVa, idA));
              _receiver.adicionaVacina((Veterinarian) e, a, v);
              return;
            }
          }
        }
        if(cnt == e.getResponsabilidadesById().size()){
          throw new VeterinarianNotAuthorizedException(idVe, a.get_specie().get_id());
        }
      }
    }
    catch(UnknownVaccineIdException uvie){
      throw new UnknownVaccineKeyException(uvie.getKey());
    }
    catch (UnknownEmployeeIdException ueie){
      throw new UnknownEmployeeKeyException(ueie.getKey());
    }
    catch (UnknownAnimalIdException uaie){
      throw new UnknownAnimalKeyException(uaie.getKey());
    }
    _display.display();
  }
}
