package com.example.alexk.herodb;

/**
 * Created by skynet on 18.10.2015.
 */
public class Hero {

        private int _id;
        private String mName;
        private String mRealName;
        private String mAbout;
        private String mWorld;
        private String mPhotoFile;
        // Пустой констуктор
        public Hero() {

        }

        // Конструктор с параметрами
        public Hero(int id, String name, String realname, String abouthero, String world, String photofile) {
            this._id = id;
            this.mName = name;
            this.mRealName = realname;
            this.mAbout = abouthero;
            this.mWorld = world;
            this.mPhotoFile = photofile;
        }

        // Конструктор с параметрами
        public Hero(String name, String realname, String abouthero, String world, String photofile) {
            this.mName = name;
            this.mRealName = realname;
            this.mAbout = abouthero;
            this.mWorld = world;
            this.mPhotoFile = photofile;
        }

        // Создание геттеров-сеттеров

        public int getID() {
            return this._id;
        }

        public void setID(int id) {
            this._id = id;
        }

        public String getName() {
            return this.mName;
        }

        public void setName(String name) {
            this.mName = name;
        }


        public String getRealName() {
        return this.mRealName;
    }

        public void setRealName(String realname) {
        this.mRealName = realname;
    }

        public String getAboutInfo() {
            return this.mAbout;
        }

        public void setAboutInfo(String abouthero) {
            this.mAbout = abouthero;
        }

        public String getWorld() {
            return this.mWorld;
        }

        public void setWorld(String world) {
            this.mWorld = world;
        }

        public String getPhotoFile() {
            return this.mPhotoFile;
        }

        public void setPhotoFile(String photofile) {
            this.mPhotoFile = photofile;
        }

//        @Override
//        public String toString() {
//            return this.mName + " Имеющаяся информация о нём: " + this.mAbout + ", из мира"+ this.mWorld;
//        }
    }


