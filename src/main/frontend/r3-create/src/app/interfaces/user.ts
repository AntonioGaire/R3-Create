import {AccessLevel} from "./access-level";

export interface User {

  idUser: number;
  username: string;
  pfp: string;
  accessLevel: AccessLevel;

  authorities :  [{ "authority": String }],
}
