import axios from "axios";

// Create Project Backlog

export const addProjectTask = (
  backlog_id,
  project_task,
  history
) => async dispatch => {
  await axios.post(`/api/backlog/addProjectTask/${backlog_id}`, project_task);
  history.push(`/projectBoard/${backlog_id}`);
};
