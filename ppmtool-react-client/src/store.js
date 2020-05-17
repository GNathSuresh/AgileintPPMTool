import { createStore, applyMiddleware, compose } from "redux";
import thunk from "redux-thunk";
import rootReducer from "./reducers";

const initialStore = {};
const middleWare = [thunk];

let store;

if (window.navigator.userAgent.includes("Chrome")) {
  store = createStore(
    rootReducer,
    initialStore,
    compose(applyMiddleware(...middleWare)),
    window.__redux_devtools_extension__ && window.__redux_devtools_extension__()
  );
} else {
  store = createStore(
    rootReducer,
    initialStore,
    compose(applyMiddleware(...middleWare))
  );
}

export default store;
