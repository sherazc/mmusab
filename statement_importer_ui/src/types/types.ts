export type RegisterUserRecord = {
  id?: number;
  userName: string;
  userPassword: string;
  registerRoleId: number;
  registerTime: string;
};

export type RegisterRole = {
  id: number;
  roleName: string;
};

export const emptyScToken = (): ScToken => ({
  token: "",
  userName: "",
  roles: "",
  createdDateTime: "",
  expiresDateTime: ""
});

export type ScToken = {
  token: string,
  userName: string,
  roles: string,
  createdDateTime: string,
  expiresDateTime: string
}
