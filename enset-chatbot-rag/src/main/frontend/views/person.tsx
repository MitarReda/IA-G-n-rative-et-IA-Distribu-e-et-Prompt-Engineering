import {AutoCrud} from "@vaadin/hilla-react-crud";
import {PersonService} from "Frontend/generated/endpoints";
import PersonModel from "Frontend/generated/com/example/ensetchatbotrag/entity/PersonModel";

export default function Person(){
    return(
        <AutoCrud service={PersonService} model={PersonModel}/>
    );
}