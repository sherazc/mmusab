import { createContext, useReducer } from "react";
import { emptyScToken, ScToken } from "../types/types";
import { ScTokenAction, scTokenReducer } from "./ScTokenReducer";

type RootStateType = {
    scToken: ScToken;
}

const initialAppState: RootStateType = {
    scToken: emptyScToken()
}

type RootAction = ScTokenAction;

const AppContext = createContext<[
    state: RootStateType,
    dispatch: React.Dispatch<RootAction>
]>([
    initialAppState,
    () => null
]);


// Combines all the reducers
const mainReducer = ({scToken}: RootStateType, action: RootAction) => ({
    scToken: scTokenReducer(scToken, action as ScTokenAction),
});

interface Props {
    children: React.ReactNode;
}

const AppProvider: React.FC<Props> = ({children}: Props) => {
    const [state, dispatch] = useReducer(mainReducer, initialAppState);

    return (
        <AppContext.Provider value={[state, dispatch]}>
            {children}
        </AppContext.Provider>
    );
};

export {AppContext, AppProvider};
