import {Button, TextField} from "@vaadin/react-components";
import {useState} from "react";
import {ChatAIService} from "Frontend/generated/endpoints";
import Markdown from "react-markdown";

export default function Chat(){
    const [question,setQuestion]=useState<string>("");
    const [response,setResponse]=useState<string>("");
async function send(){
 ChatAIService.ragChat(question).then(respon=>{
     setResponse(respon);
 });
}
    return(
        <div>
            <h3>Chat Bot</h3>
            <div>
                <TextField style={{width:'80%'}} onChange={(e=>setQuestion(e.target.value))}/>
                <Button theme="primary" onClick={send}>Send</Button>
                <div>
                    <Markdown>
                        {response}
                    </Markdown>

                </div>
            </div>
        </div>
    );
}