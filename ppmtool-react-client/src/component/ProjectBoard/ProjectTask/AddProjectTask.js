import React, { Component } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import classnames from "classnames";
import PropTypes from "prop-types";
import { addProjectTask } from "../../../actions/backlogActions";

class AddProjectTask extends Component {
  constructor(props) {
    super(props);
    const { id } = this.props.match.params;
    this.state = {
      summary: "",
      acceptanceCriteria: "",
      dueDate: "",
      priority: 0,
      status: "",
      projectIdentifier: id,
      errors: {}
    };

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();
    const newProjectTask = {
      summary: this.state.summary,
      acceptanceCriteria: this.state.acceptanceCriteria,
      dueDate: this.state.dueDate,
      priority: this.state.priority,
      status: this.state.status,
      projectIdentifier: this.state.projectIdentifier
    };

    //console.log(newProject);
    this.props.addProjectTask(
      this.state.projectIdentifier,
      newProjectTask,
      this.props.history
    );
  }

  render() {
    const { id } = this.props.match.params;
    const { errors } = this.state;

    return (
      <div className="add-PBI">
      {
        // Add Project Items Form Html
        // create Constructor
        // set state
        // set value on input fields
        // create onChange function
        // set onChange on each input field
        // bind on consturctor
        // check state change in react extension
      }
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <Link to={`/projectBoard/${id}`} className="btn btn-light">
                Back to Project Board
              </Link>
              <h4 className="display-4 text-center">Add Project Task</h4>
              <p className="lead text-center">Project Name + Project Code</p>
              <form onSubmit={this.onSubmit}>
                <div className="form-group">
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.summary
                      })}
                      name="summary"
                      value={this.state.summary}
                      onChange={this.onChange}
                      placeholder="Project Task summary"
                    />
                    {errors.summary && (
                      <div className="invalid-feedback">{errors.summary}</div>
                    )}
                  </div>
                </div>
                <div className="form-group">
                  <textarea
                    className={classnames("form-control form-control-lg", {
                      "is-invalid": errors.acceptanceCriteria
                    })}
                    placeholder="Acceptance Criteria"
                    name="acceptanceCriteria"
                    onChange={this.onChange}
                    value={this.state.acceptanceCriteria}
                  ></textarea>
                  {errors.acceptanceCriteria && (
                    <div className="invalid-feedback">
                      {errors.acceptanceCriteria}
                    </div>
                  )}
                </div>
                <h6>Due Date</h6>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="dueDate"
                    onChange={this.onChange}
                    value={this.state.dueDate}
                  />
                </div>
                <div className="form-group">
                  <select
                    className={classnames("form-control form-control-lg", {
                      "is-invalid": errors.priority
                    })}
                    name="priority"
                    onChange={this.onChange}
                    value={this.state.priority}
                  >
                    <option value={0}>Select Priority</option>
                    <option value={1}>High</option>
                    <option value={2}>Medium</option>
                    <option value={3}>Low</option>
                  </select>
                </div>

                <div className="form-group">
                  <select
                    className="form-control form-control-lg"
                    name="status"
                    onChange={this.onChange}
                    value={this.state.status}
                  >
                    <option value="">Select Status</option>
                    <option value="TO_DO">TO DO</option>
                    <option value="IN_PROGRESS">IN PROGRESS</option>
                    <option value="DONE">DONE</option>
                  </select>
                </div>

                <input
                  type="submit"
                  className="btn btn-primary btn-block mt-4"
                />
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

AddProjectTask.propTypes = {
  addProjectTask: PropTypes.func.isRequired
};
export default connect(null, { addProjectTask })(AddProjectTask);
