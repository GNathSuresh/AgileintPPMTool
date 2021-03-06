import axios from "axios";
import {GET_ERRORS} from "./types"

// Create Project Backlog

export const addProjectTask = (
  backlog_id,
  project_task,
  history
) => async dispatch => {
    try
    {
        await axios.post(`/api/backlog/addProjectTask/${backlog_id}`, project_task);
        history.push(`/projectBoard/${backlog_id}`);
        dispatch({
            type: GET_ERRORS,
            payload: {}
        });
    }
    catch(error)
    {
        dispatch({
            type: GET_ERRORS,
            payload: error.response.data
          });    
    }
};
