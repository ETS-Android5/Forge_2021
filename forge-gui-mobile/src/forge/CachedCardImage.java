package forge;

import com.badlogic.gdx.graphics.Texture;
import forge.assets.ImageCache;
import forge.game.card.CardView;
import forge.item.InventoryItem;
import forge.util.ImageFetcher;

public abstract class CachedCardImage implements ImageFetcher.Callback {
    protected final String key;
    static final ImageFetcher fetcher = GuiBase.getInterface().getImageFetcher();

    public CachedCardImage(final CardView card) {
        key = card.getCurrentState().getImageKey();
        fetch();
    }

    public CachedCardImage(final InventoryItem ii) {
        key = ii.getImageKey(false);
        fetch();
    }

    public CachedCardImage(String key) {
        this.key = key;
        fetch();
    }

    public void fetch() {
        Texture image = ImageCache.getImage(key, false);
        if (image == null) {
            fetcher.fetchImage(key, this);
        }
    }

    public Texture getImage() {
        return ImageCache.getImage(key, true);
    }

    public abstract void onImageFetched();
}