import React, { Component } from "react";
import "./App.css";
import Dashboard from "./component/Dashboard";
import Header from "./component/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./component/Project/AddProject";
import { Provider } from "react-redux";
import store from "./store";
import UpdateProject from "./component/Project/UpdateProject";
import ProjectBoard from "./component/ProjectBoard/ProjectBoard";
import AddProjectTask from "./component/ProjectBoard/ProjectTask/AddProjectTask";

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <div className="App">
            <Header />
            <Route exact path="/dashBoard" component={Dashboard}></Route>
            <Route exact path="/addProject" component={AddProject}></Route>
            <Route
              exact
              path="/updateProjectInfo/:id"
              component={UpdateProject}
            ></Route>
            <Route
              exact
              path="/projectBoard/:id"
              component={ProjectBoard}
            ></Route>
            <Route
              exact
              path="/addProjectTask/:id"
              component={AddProjectTask}
            ></Route>
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
